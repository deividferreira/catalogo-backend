package br.eti.deividferreira.catalogo.infra.misc;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Translator {

	@Setter
	private static MessageSource messageSource;

	public static String translate(String messageCode, Object... parameters) {
		return messageSource.getMessage(messageCode, parameters, LocaleContextHolder.getLocale());
	}	
}
