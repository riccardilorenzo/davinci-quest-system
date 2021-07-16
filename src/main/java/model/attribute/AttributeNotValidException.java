package model.attribute;

import java.io.Serial;

public class AttributeNotValidException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;
	
	public AttributeNotValidException() { }

	public AttributeNotValidException(String message) {
		super(message);
	}

	public AttributeNotValidException(Throwable inner) {
		super(inner);
	}

	public AttributeNotValidException(String message, Throwable inner) {
		super(message, inner);
	}
}
