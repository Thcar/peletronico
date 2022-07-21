package br.com.projeto.peletronico.exception;

public class InvalidException extends RuntimeException {

	public InvalidException(String messenger) {
		super(messenger);
	}

}
