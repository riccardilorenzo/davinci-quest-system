package controller;

import model.Attribute;
import persistence.Reader;
import persistence.Writer;

/**
 * Provides a controller for commanders, which interfaces with the DB.
 * @author TheMind
 */
public class CommanderController {
    public static final int MAX_ATTRIBUTE_BASE_POINTS = Attribute.values().length * 5;
    public static final int MAX_ATTRIBUTE_POINTS = MAX_ATTRIBUTE_BASE_POINTS + 10;
    private Reader reader;
    private Writer writer;

    public CommanderController(Reader reader, Writer writer) {
        if (writer == null || reader == null) throw new IllegalArgumentException("Reader or writer null, aborting.");

        this.reader = reader;
        this.writer = writer;
    }
}
