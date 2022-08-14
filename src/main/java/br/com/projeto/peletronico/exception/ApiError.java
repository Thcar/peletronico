package br.com.projeto.peletronico.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiError {
	// property holds the operation call status.
	private HttpStatus status;
	// property holds the date-time instance of when the error happened.
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	// property holds a user-friendly message about the error.
	private String message;
	// property holds a system message describing the error in more detail.
	private String debugMessage;
	// This is used for representing multiple errors in a single call
	private List<ApiSubError> subError;

	private ApiError() {
		this.timestamp = LocalDateTime.now();
	}

	ApiError(HttpStatus status) {
		this();
		this.status = status;
	}

	ApiError(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();

	}

	ApiError(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();

	}

	public HttpStatus getStatus() {
		return status;
	}

}
