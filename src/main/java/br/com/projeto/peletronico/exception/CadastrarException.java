package br.com.projeto.peletronico.exception;

public class CadastrarException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public CadastrarException(String mensagem) {
		super(mensagem);
	}

}
