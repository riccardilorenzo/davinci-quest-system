package model.mission.requirement;

public record MarketRequirement(int amountToSell) implements Requirement {

    /**
     * @see Requirement#isMet(String status)
     */
    @Override
    public boolean isMet(String status) {
        // TODO: parse JSON from 'status' and get the sell
        return true;
    }

    @Override
    public String toString() {
        return "(Market) Richiesto: " + this.amountToSell;
    }
}
