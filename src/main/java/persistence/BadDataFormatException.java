package persistence;

import java.io.Serial;

public class BadDataFormatException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public BadDataFormatException() { }

    public BadDataFormatException(String message) {
        super(message);
    }

    public BadDataFormatException(Throwable inner) {
        super(inner);
    }

    public BadDataFormatException(String message, Throwable inner) {
        super(message, inner);
    }
}
