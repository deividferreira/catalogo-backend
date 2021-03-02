package br.eti.deividferreira.legalpecas.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ApplicationRuntimeException extends RuntimeException {

	@Getter
    protected Object[] parameters;

	
    public ApplicationRuntimeException() {
        this.parameters = new Object[]{};
    }

    public ApplicationRuntimeException(String message) {
        super(message);
    }

    public ApplicationRuntimeException(String message, Object... parameters) {
        super(message);
        this.parameters = parameters;
    }

    public ApplicationRuntimeException(String message, Throwable throwable, Object... parameters) {
        super(message, throwable);
        this.parameters = parameters;
    }
}
