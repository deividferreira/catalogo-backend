package br.eti.deividferreira.catalogo.infra.spring.init;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.eti.deividferreira.catalogo.domain.entities.security.Authorities;
import br.eti.deividferreira.catalogo.domain.entities.security.Grant;
import br.eti.deividferreira.catalogo.domain.repositories.security.AuthorityRepository;
import br.eti.deividferreira.catalogo.domain.repositories.security.GrantRepository;
import br.eti.deividferreira.catalogo.domain.repositories.security.UserRepository;
import lombok.AllArgsConstructor;

@Order(1)
@Component
@AllArgsConstructor
public class GrantAdminAuthorityTask implements InitialTask {

	private final UserRepository userRepository;
	private final GrantRepository grantRepository;
	private final AuthorityRepository authorityRepository;

	@Override
	public void perform() {

		final var admin = this.userRepository.findByUsername("admin")
				.orElseThrow(() -> new IllegalStateException("Admin user not found, create it and try again"));

		final var adminAuthority = this.authorityRepository.findByName(Authorities.ADMINISTRATOR).orElseThrow(
				() -> new IllegalStateException("Administrator authority not found, create it and try again"));

		this.grantRepository.findByUserAndAuthority(admin, adminAuthority).ifPresentOrElse(grant -> {
			/* do nothing */}, () -> this.grantRepository.save(new Grant(admin, adminAuthority)));
	}
}
