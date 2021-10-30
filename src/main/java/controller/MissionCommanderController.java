package controller;

import model.Attribute;
import model.Commander;
import model.QuestException;
import model.mission.Ending;
import model.mission.Mission;
import model.mission.MissionStatus;
import model.mission.Outcome;
import model.mission.requirement.Requirement;
import persistence.BadDataFormatException;
import persistence.Reader;
import persistence.Writer;
import persistence.WriterException;

import java.util.Optional;
import java.util.Random;
import java.util.StringJoiner;

/**
 * Provides a controller to link a Commander and its Mission(s), which interfaces with the DB.
 */
@SuppressWarnings("ClassCanBeRecord")
public class MissionCommanderController {
    /**
     * Maximum amount of Attribute values (summed) the Commander can ever have (considering also Mission bonuses).
     */
    public static final int MAX_ATTRIBUTE_POINTS = Commander.MAX_ATTRIBUTE_BASE_POINTS + 15;

    private final Writer writer;
    private final Reader reader;

    /**
     * Main constructor for this class.
     * @param reader The source reader.
     * @param writer The source writer.
     */
    public MissionCommanderController(Reader reader, Writer writer) {
        if (writer == null || reader == null) throw new IllegalArgumentException("Reader or Writer null, aborting.");
        this.writer = writer; this.reader = reader;
    }

    /**
     * Accepts the specified Mission for the specified Commander.
     * @param cmdr The Commander who accepts the Mission.
     * @param m The Mission to accept.
     * @return True if the Mission can be accepted by the Commander (and accepts it), false otherwise.
     */
    public boolean acceptMission(Commander cmdr, Mission m) {
        if (cmdr == null || m == null) throw new IllegalArgumentException("Commander or Mission null, aborting.");

        MissionStatus status;
        try {
            status = reader.readMissionStatus(cmdr, m);
            if (!status.equals(MissionStatus.NOT_ACCEPTED) && !status.equals(MissionStatus.FAILED))
                return false;
        } catch (BadDataFormatException e) { return false; }

        if (m.getNeededPoints() <= cmdr.getPoints()) {
            for (Attribute attr : m.getAttributeRequirements().keySet()) {
                if (!cmdr.getAttributes().containsKey(attr)
                    || m.getAttributeRequirements().get(attr) > cmdr.getAttributes().get(attr))
                    return false;
            }

            try {
                writer.updateMissionStatus(cmdr, m, MissionStatus.PENDING, 0);
                cmdr.addPoints(-m.getNeededPoints());
                writer.updateCommander(cmdr);
            } catch (WriterException e) {
                throw new QuestException("E' stato rilevato un errore in fase di salvataggio dei nuovi dati, contatta un Amministratore.");
            }
            return true;
        }
        return false;
    }

    /**
     * Advances the given Commander of one Step in the specified Mission. If possible, completes the Mission.
     * @param cmdr The Commander to advance.
     * @param m The Mission to be advanced.
     * @param status A String, JSON parsable, that contains an in-game mission.
     * @return An Outcome object, with the false value in case of errors or failure of the Step, true otherwise. The String specifies the outcome.
     */
    public Outcome advanceMission(Commander cmdr, Mission m, String status) {
        int stepIndex;
        try {
            stepIndex = reader.readLastStepIndex(cmdr, m);
            if (stepIndex == -1) return new Outcome(false, "Non hai mai accettato questa missione.");

            if (!reader.readMissionStatus(cmdr, m).equals(MissionStatus.PENDING))
                return new Outcome(false, "La missione è stata già completata o fallita! In quest'ultimo caso puoi ritentarla!");
        } catch (BadDataFormatException e) { return new Outcome(false, "Errore in lettura, per favore contatta un Amministratore."); }

        // TODO: !!! status is only one for multiple, different, requirements: rethink this or use only one requirement for step
        for (Requirement req : m.getSteps().get(stepIndex).getRequirements()) {
            // TODO: !!! rethink the status object, as if made so, it would require one delivery only (one JSON object contains only one IG mission -> maybe concat?)
            // TODO: !!! with the MarketRequirement made so, I can sell to Beagle Point instead of Atorii without any problem
            if (!req.isMet(status))
                return new Outcome(false, "Non soddisfi il seguente requisito per il completamento dello Step: " + System.lineSeparator() + req);
        }

        if (stepIndex == m.getSteps().size() - 1) return completeMission(cmdr, m);

        try {
            writer.updateMissionStepIndex(cmdr, m, stepIndex + 1);
        } catch (WriterException e) {
            throw new QuestException("E' stato rilevato un errore in fase di salvataggio dei nuovi dati, contatta un Amministratore.");
        }

        return new Outcome(true, "Hai completato uno step di questa missione, procedi!");
    }

    private Outcome completeMission(Commander cmdr, Mission m) {
        // TODO: consider a random Ending influenced ALSO by attributes
        Optional<Ending> ending = m.getEndings().parallelStream().skip(new Random().nextInt(m.getEndings().size())).findFirst();
        int numberOfAttributes = cmdr.getAttributes().values().parallelStream().mapToInt(Integer::valueOf).sum();
        if (ending.isPresent()) {
            for (Attribute attr : ending.get().getRewards().keySet()) {
                int reward = ending.get().getRewards().get(attr);
                if (numberOfAttributes + reward < MAX_ATTRIBUTE_POINTS) {
                    int attrValue = cmdr.getAttributes().getOrDefault(attr, 0);
                    cmdr.setAttribute(attr, attrValue + reward);
                    numberOfAttributes += reward;
                } else {
                    cmdr.setAttribute(attr,
                            MAX_ATTRIBUTE_POINTS - numberOfAttributes + cmdr.getAttributes().getOrDefault(attr, 0));
                    break;
                }
            }
        }
        try {
            writer.updateMissionStatus(cmdr, m, MissionStatus.SUCCESS);
            cmdr.addPoints(m.getRewardPoints());
            writer.updateCommander(cmdr);
        } catch (WriterException e) {
            throw new QuestException("E' stato rilevato un errore in fase di salvataggio dei nuovi dati, contatta un Amministratore.");
        }

        return new Outcome(true, formatSuccess(m, ending));
    }

    /**
     * Retires a Commander from a Mission, failing that.
     * @param cmdr The Commander who retires.
     * @param m The Mission to fail.
     */
    public void retireFromMission(Commander cmdr, Mission m) {
        try {
            writer.updateMissionStatus(cmdr, m, MissionStatus.FAILED);
        } catch (WriterException e) {
            throw new QuestException("Si è verificato un errore durante il rifiuto della missione, contatta un Amministratore. Nel frattempo, vedila come un'occasione per portarla a termine! :)");
        }
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private String formatSuccess(Mission m, Optional<Ending> ending) {
        StringJoiner sj = new StringJoiner(System.lineSeparator(), System.lineSeparator(), "");
        if (ending.isPresent()) {
            sj.add("Un imprevisto, al termine delle tue imprese, ha portato infine alle seguenti modifiche nelle tue abilità:");
            for (Attribute attr : ending.get().getRewards().keySet())
                sj.add("\t- " + attr + ": " + ending.get().getRewards().get(attr));
            sj.add(System.lineSeparator() + ending.get().getText());
        }
        return "Hai completato con successo la missione, complimenti!" + System.lineSeparator()
                + "Inoltre, hai guadagnato " + m.getRewardPoints() + " punti!" + sj;
    }

    /**
     * Reads the MissionStatus relative to the given Commander and Mission.
     * @param cmdr The Commander.
     * @param m The Mission.
     * @return The read MissionStatus. MissionStatus.NOT_ACCEPTED if not found.
     */
    public MissionStatus readMissionStatus(Commander cmdr, Mission m) {
        try {
            return reader.readMissionStatus(cmdr, m);
        } catch (BadDataFormatException e) {
            throw new QuestException("Si è verificato un errore durante la lettura dello stato di una missione, contatta un Amministratore.");
        }
    }
}
