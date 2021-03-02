package br.eti.deividferreira.legalpecas.infra.spring;

import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import br.eti.deividferreira.legalpecas.infra.misc.Translator;

@EnableAsync
@Configuration
@EnableScheduling
public class CommonConfiguration {
	
	@Bean
    public MessageSource configureMessages() {
        Locale.setDefault(new Locale("pt", "BR"));

        final ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setCacheSeconds(0);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setAlwaysUseMessageFormat(true);

        messageSource.setBasenames(
                "classpath:i18n/messages",
                "classpath:i18n/mail"
        );

        Translator.setMessageSource(messageSource);

        return messageSource;
    }


	@Bean
    public ModelMapper modelMapper() {
        final ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration()
                .setFieldAccessLevel(AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        return mapper;
    }

}
