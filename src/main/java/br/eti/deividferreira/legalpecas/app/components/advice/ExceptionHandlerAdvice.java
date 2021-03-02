package br.eti.deividferreira.legalpecas.app.components.advice;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.eti.deividferreira.legalpecas.domain.exceptions.BusinessLogicException;
import br.eti.deividferreira.legalpecas.infra.misc.Translator;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler({ IllegalArgumentException.class, IllegalStateException.class })
	public void handleIllegalArgumentException(HttpServletResponse response, RuntimeException exception)
			throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), this.translate(exception.getMessage()));
	}

	@ExceptionHandler(BusinessLogicException.class)
	public void handleIllegalArgumentException(HttpServletResponse response, BusinessLogicException exception)
			throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(),
				this.translate(exception.getMessage(), exception.getParameters()));
	}

	@ExceptionHandler(AccessDeniedException.class)
	public void handleIllegalAccessDenied(HttpServletResponse response, AccessDeniedException exception)
			throws IOException {
		response.sendError(HttpStatus.FORBIDDEN.value(), this.translate(exception.getMessage()));
	}

	private String translate(String messageCode, Object... parameters) {
		return Translator.translate(messageCode, parameters);
	}

}
