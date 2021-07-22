package controller;

import model.Attribute;
import persistence.Reader;
import persistence.Writer;

/**
 * Provides a controller for missions, which interfaces with the DB.
 * @author TheMind
 */
public class MissionController {
    public static final int MAX_REQUIREMENT_POINTS = Attribute.values().length * 5;
    private Reader reader;
    private Writer writer;

    public MissionController(Reader reader, Writer writer) {
        if (reader == null || writer == null) throw new IllegalArgumentException("Reader or writer null, aborting.");

        this.reader = reader;
        this.writer = writer;
    }
}
