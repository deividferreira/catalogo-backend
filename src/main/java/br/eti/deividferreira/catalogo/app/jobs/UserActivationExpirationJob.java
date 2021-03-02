package br.eti.deividferreira.catalogo.app.jobs;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.eti.deividferreira.catalogo.domain.entities.security.ActivationStatus;
import br.eti.deividferreira.catalogo.domain.entities.security.UserActivation;
import br.eti.deividferreira.catalogo.domain.repositories.UserActivationRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserActivationExpirationJob {

	private final UserActivationRepository userActivationRepository;

	@Scheduled(cron = "0 0/30 * * * ?")
	public void updateExpiredActivations() {
		final var activations = this.userActivationRepository.findByActivationStatus(ActivationStatus.WAITING);

		activations.stream().filter(UserActivation::isExpired)
				.forEach(activation -> this.userActivationRepository.save(activation.expire()));
	}

}
