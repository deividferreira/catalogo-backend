package br.eti.deividferreira.legalpecas.domain.repositories.security;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.eti.deividferreira.legalpecas.domain.entities.security.Authority;
import br.eti.deividferreira.legalpecas.domain.entities.security.Grant;
import br.eti.deividferreira.legalpecas.domain.entities.security.User;
import br.eti.deividferreira.legalpecas.domain.repositories.DefaultRepository;

public interface GrantRepository extends DefaultRepository<Grant> {

	Optional<Grant> findByUserAndAuthority(User admin, Authority authority);

	void deleteByUser_id(UUID id);

	List<Grant> findByUser(User user);

}
