package de.converter.abap.generator;

public class CodeGenerationException extends Exception {

	private static final long serialVersionUID = 3906033341323845529L;

	public CodeGenerationException(String message) {
		super(message);
	}

	public CodeGenerationException(Throwable cause) {
		super(cause);
	}

	public CodeGenerationException(String message, Throwable cause) {
		super(message, cause);
	}

}
