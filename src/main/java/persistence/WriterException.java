package persistence;

import java.io.Serial;

public class WriterException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public WriterException() { }

    public WriterException(String message) {
        super(message);
    }

    public WriterException(Throwable inner) {
        super(inner);
    }

    public WriterException(String message, Throwable inner) {
        super(message, inner);
    }
}