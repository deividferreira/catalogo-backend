package br.eti.deividferreira.legalpecas.app.components;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.eti.deividferreira.legalpecas.domain.entities.security.User;
import br.eti.deividferreira.legalpecas.domain.repositories.security.UserRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AuthenticatedUser {

	private final UserRepository userRepository;

	public Optional<User> get() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return this.userRepository.findByUsername(authentication.getName());
	}

}
