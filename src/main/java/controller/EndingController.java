package controller;

import persistence.Reader;
import persistence.Writer;

public class EndingController {
    private Writer writer;
    private Reader reader;

    public EndingController(Reader reader, Writer writer) {
        if (writer == null || reader == null) throw new IllegalArgumentException("Reader or writer null, aborting.");

        this.reader = reader;
        this.writer = writer;
    }
}
