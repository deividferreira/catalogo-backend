package br.eti.deividferreira.catalogo.domain.services;

import static br.eti.deividferreira.catalogo.infra.misc.Translator.translate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eti.deividferreira.catalogo.app.components.MailMessage;
import br.eti.deividferreira.catalogo.domain.dto.RegistrationForm;
import br.eti.deividferreira.catalogo.domain.entities.security.ActivationStatus;
import br.eti.deividferreira.catalogo.domain.entities.security.User;
import br.eti.deividferreira.catalogo.domain.entities.security.UserActivation;
import br.eti.deividferreira.catalogo.domain.exceptions.BusinessLogicException;
import br.eti.deividferreira.catalogo.domain.repositories.UserActivationRepository;
import br.eti.deividferreira.catalogo.domain.repositories.security.UserRepository;
import br.eti.deividferreira.catalogo.infra.misc.PasswordGenerator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserAccountService {

	@Value("${backend-legal-pecas.application-url}")
	private String applicationUrl;
	@Value("${spring.application.name}")
	private String applicationName;

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserActivationRepository userActivationRepository;

	private final ApplicationEventPublisher eventPublisher;

	@Transactional
	public void register(RegistrationForm registrationForm) {
		final var user = new User();
		user.setName(registrationForm.getName());
		user.setUsername(registrationForm.getUsername());
		user.setLastName(registrationForm.getLastName());
		user.setEmail(registrationForm.getEmail());
		user.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
		final var userSaved = this.userRepository.save(user);

		this.requestActivation(userSaved);
	}

	@Transactional
	public void requestActivation(User user) {
		final var activation = this.userActivationRepository.save(new UserActivation(user));

		final String activationLink = this.applicationUrl + "/user-account/activate/" + activation.getToken();

		final var mailMessage = buildMailMessageUserAccount(user, "account-activation");
		mailMessage.setSubject(translate("account-activation.subject"));
		mailMessage.addProperty("link", activationLink);

		this.eventPublisher.publishEvent(mailMessage);
	}

	@Transactional
	public void activate(String token) {
		this.userActivationRepository.findByTokenAndActivationStatus(token, ActivationStatus.WAITING)
				.ifPresentOrElse(activation -> {
					this.userRepository.save(activation.getUser().enable());
					this.userActivationRepository.save(activation.activate());
				}, () -> {
					throw new BusinessLogicException(translate("error.user-activation.token-not-found"));
				});
	}

	@Transactional
	public void recoverPassword(String email) {
		this.userRepository.findByEmail(email).ifPresentOrElse(user -> {
			final String newPassword = PasswordGenerator.generate();
			user.setPassword(this.passwordEncoder.encode(newPassword));

			this.userRepository.save(user);

			final var mailMessage = buildMailMessageUserAccount(user, "password-recover");
			mailMessage.setSubject(translate("password-recover.subject"));
			mailMessage.addProperty("password", newPassword);

			this.eventPublisher.publishEvent(mailMessage);
		}, () -> {
			throw new BusinessLogicException(translate("error.user-activation.email-not-found"));
		});
	}

	public MailMessage buildMailMessageUserAccount(final User user, final String templateName) {
		final var mailMessage = new MailMessage();

		mailMessage.setTemplateName(templateName);
		mailMessage.setTo(user.getEmail());
		mailMessage.setFrom(translate("account-activation.from"));

		mailMessage.addProperty("name", user.getName());

		mailMessage.addProperty("applicationName", this.applicationName);
		mailMessage.addProperty("applicationUrl", this.applicationUrl + "/");
		mailMessage.addProperty("imageLogo", this.applicationUrl + "/logo.png");

		return mailMessage;
	}

}
