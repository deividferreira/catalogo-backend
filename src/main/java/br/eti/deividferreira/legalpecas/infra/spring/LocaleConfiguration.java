package br.eti.deividferreira.legalpecas.infra.spring;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import br.eti.deividferreira.legalpecas.infra.misc.Translator;

@Configuration
public class LocaleConfiguration extends AcceptHeaderLocaleResolver {

	private final List<Locale> locales;

	public LocaleConfiguration() {
		this.locales = List.of(
				new Locale("pt", "BR"), 
				new Locale("en", "US"));
	}

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String headerLang = request.getHeader("Accept-Language");
		return headerLang == null || headerLang.isEmpty() ? Locale.getDefault()
				: Locale.lookup(Locale.LanguageRange.parse(headerLang), this.locales);
	}
	
	@Bean
    public MessageSource messageSource() {
        Locale.setDefault(new Locale("pt", "BR"));

        final var messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setAlwaysUseMessageFormat(true);
        messageSource.setUseCodeAsDefaultMessage(false);

        messageSource.setBasenames("classpath:i18n/messages", "classpath:i18n/mail");

        Translator.setMessageSource(messageSource);

        return messageSource;
    }
	
	@Bean
    public LocalValidatorFactoryBean getValidator() {
        final var validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(this.messageSource());
        return validatorFactoryBean;
    }

}
