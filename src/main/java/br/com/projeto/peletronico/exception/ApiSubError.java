package br.com.projeto.peletronico.exception;

//This is used for representing multiple errors in a single call.
public abstract class ApiSubError {
	

}

class ApiValidationError extends ApiSubError {
	
	private String object;
	private String field;
	private Object rejectedValue;
	private String message;
	
	ApiValidationError(String object, String message){
		this.object = object;
		this.message = message;
		
	}
	
}
