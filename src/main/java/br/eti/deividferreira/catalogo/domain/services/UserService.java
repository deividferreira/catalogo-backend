package br.eti.deividferreira.catalogo.domain.services;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eti.deividferreira.catalogo.domain.entities.security.Authority;
import br.eti.deividferreira.catalogo.domain.entities.security.Grant;
import br.eti.deividferreira.catalogo.domain.entities.security.User;
import br.eti.deividferreira.catalogo.domain.repositories.UserActivationRepository;
import br.eti.deividferreira.catalogo.domain.repositories.security.GrantRepository;
import br.eti.deividferreira.catalogo.domain.repositories.security.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {
	private final UserRepository userRepository;
	private final GrantRepository grantRepository;
	private final UserActivationRepository userActivationRepository;

	private final PasswordEncoder encoder;

	@Transactional
	public void save(User user, List<Authority> authorities) {
		user.setPassword(this.encoder.encode(user.getPassword()));
		this.userRepository.save(user);
		
		authorities.forEach(authority -> this.grantRepository.save(new Grant(user, authority)));
	}

	@Transactional
	public User update(User user, List<Authority> authorities) {
		// if password is blank, keep the actual password, if not, encode and update a
		// new one base on password field
		if (isBlank(user.getPassword())) {
			this.userRepository.findById(user.getId()).ifPresent(found -> user.setPassword(found.getPassword()));
		} else {
			final var encoded = this.encoder.encode(user.getPassword());
			user.setPassword(encoded);
		}

		this.userRepository.save(user);

		if (authorities != null && !authorities.isEmpty()) {
			this.grantRepository.deleteByUser_id(user.getId());
			authorities.forEach(authority -> this.grantRepository.save(new Grant(user, authority)));
		}

		final var grants = this.grantRepository.findByUser(user);
		user.setGrants(grants);

		return user;
	}

	@Transactional
	public void delete(UUID userId) {
		this.userRepository.findById(userId).ifPresent(user -> {
			this.grantRepository.deleteByUser_id(userId);
			this.userActivationRepository.deleteByUser_id(userId);
			this.userRepository.delete(user);
		});
	}

}
