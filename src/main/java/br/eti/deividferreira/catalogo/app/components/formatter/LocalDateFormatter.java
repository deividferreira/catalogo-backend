package br.eti.deividferreira.catalogo.app.components.formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class LocalDateFormatter implements Formatter<LocalDate> {

	private static final String PATTERN = "yyyy-MM-dd";

	@Override
	public String print(LocalDate localDate, Locale locale) {
		return localDate.format(DateTimeFormatter.ofPattern(PATTERN));
	}

	@Override
	public LocalDate parse(String text, Locale locale) throws ParseException {
		return StringUtils.isNotBlank(text) ? LocalDate.parse(text, DateTimeFormatter.ofPattern(PATTERN)) : null;
	}

}
