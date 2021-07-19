package model;

import java.io.Serial;

public class QuestException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;
	
	public QuestException() { }

	public QuestException(String message) {
		super(message);
	}

	public QuestException(Throwable inner) {
		super(inner);
	}

	public QuestException(String message, Throwable inner) {
		super(message, inner);
	}
}
