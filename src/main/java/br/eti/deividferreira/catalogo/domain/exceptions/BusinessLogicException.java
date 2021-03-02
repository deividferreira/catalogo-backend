package br.eti.deividferreira.catalogo.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessLogicException extends ApplicationRuntimeException {

	public BusinessLogicException() {
        this(null, new Object[]{});
    }

    public BusinessLogicException(String message) {
        super(message);
    }

    public BusinessLogicException(String message, Object... parameters) {
        super(message);
        this.parameters = parameters;
    }

    public BusinessLogicException(String message, Throwable throwable, Object... parameters) {
        super(message, throwable);
        this.parameters = parameters;
    }
}
