package br.eti.deividferreira.catalogo.domain.repositories.security;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import br.eti.deividferreira.catalogo.domain.entities.security.User;
import br.eti.deividferreira.catalogo.domain.repositories.DefaultRepository;

public interface UserRepository extends DefaultRepository<User> {
	
	Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email);

	Optional<User> findById(UUID userId);

	@Query("from User u where filter(:filter, u.name, u.email, u.username) = true")
    Page<User> findByFilter(String filter, Pageable pageable);
}
