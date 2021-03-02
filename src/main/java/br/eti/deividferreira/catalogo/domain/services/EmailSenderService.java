package br.eti.deividferreira.catalogo.domain.services;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import br.eti.deividferreira.catalogo.app.components.MailMessage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@AllArgsConstructor
public class EmailSenderService {
	
	private final JavaMailSender emailSender;
	private final SpringTemplateEngine templateEngine;
	
	@EventListener @Async
	public void sendEmail(MailMessage mail) {
		log.info("Iniciando envio de e-mail! Destinatário: {}", mail.getTo());
		try {
			MimeMessage message = emailSender.createMimeMessage();
	        MimeMessageHelper helper;
			helper = new MimeMessageHelper(message,
			        MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
			        StandardCharsets.UTF_8.name());
			
			helper.setTo(mail.getTo());
			helper.setFrom(mail.getFrom());
			helper.setSubject(mail.getSubject());
			helper.setText(getHtmlContent(mail), true);
			
			emailSender.send(message);
			log.info("Email enviado com sucesso! Destinatário: {}", mail.getTo());        
		} catch (MessagingException | IllegalArgumentException e) {
			log.error("Falha ao enviar email para {}! Erro apresentado: {}", mail.getTo(), e.getMessage());
			e.printStackTrace();
		}
	}
	
	private String getHtmlContent(MailMessage mail) {
        Context context = new Context();
        context.setVariables(mail.getProperties());
        return templateEngine.process(mail.getTemplateName(), context);
    }

}
