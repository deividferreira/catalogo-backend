package br.eti.deividferreira.legalpecas.infra.spring.init;

import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.eti.deividferreira.legalpecas.domain.entities.security.User;
import br.eti.deividferreira.legalpecas.domain.repositories.security.UserRepository;
import lombok.AllArgsConstructor;

@Order(0)
@Component
@AllArgsConstructor
public class CreateAdminTask implements InitialTask {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void perform() {
		this.userRepository.findByUsername("admin").ifPresentOrElse(saved -> {
			/* empty */}, () -> {

				final var user = new User();

				user.setName("Administrador");
				user.setLastName("Sistema");
				user.setUsername("admin");
				user.setEmail("admin@admin.com");
				user.setPassword(this.passwordEncoder.encode("admin"));
				user.enable();

				this.userRepository.save(user);
			});
	}
}
