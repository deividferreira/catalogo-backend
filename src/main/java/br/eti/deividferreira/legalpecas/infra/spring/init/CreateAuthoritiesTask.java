package br.eti.deividferreira.legalpecas.infra.spring.init;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.eti.deividferreira.legalpecas.domain.entities.security.Authorities;
import br.eti.deividferreira.legalpecas.domain.entities.security.Authority;
import br.eti.deividferreira.legalpecas.domain.repositories.security.AuthorityRepository;
import lombok.AllArgsConstructor;

@Order(0)
@Component
@AllArgsConstructor
public class CreateAuthoritiesTask implements InitialTask {

	private final AuthorityRepository authorityRepository;

	@Override
	public void perform() {
		Authorities.ALL_AUTHORITIES
				.forEach(name -> this.authorityRepository.findByName(name).ifPresentOrElse(authority -> {
					/* do nothing */ }, () -> this.authorityRepository.save(new Authority(name))));
	}
}
