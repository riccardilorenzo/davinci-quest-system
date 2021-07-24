package controller;

import model.Commander;
import model.QuestException;
import persistence.BadDataFormatException;
import persistence.Reader;
import persistence.Writer;
import persistence.WriterException;

/**
 * Provides a controller for commanders, which interfaces with the DB.
 * @author TheMind
 */
public class CommanderController {
    private Reader reader;
    private Writer writer;

    public CommanderController(Reader reader, Writer writer) {
        if (writer == null || reader == null) throw new IllegalArgumentException("Reader or writer null, aborting.");

        this.reader = reader;
        this.writer = writer;
    }

    public Commander getCommander(String login) {
        try {
            return reader.readCommander(login);
        } catch (BadDataFormatException e) {
            throw new QuestException(e.getLocalizedMessage());
        }
    }

    public void saveCommander(Commander cmdr) {
        try {
            writer.updateCommander(cmdr);
        } catch (WriterException e) {
            throw new QuestException(e.getLocalizedMessage());
        }
    }
}
