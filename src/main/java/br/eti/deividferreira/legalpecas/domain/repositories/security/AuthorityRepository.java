package br.eti.deividferreira.legalpecas.domain.repositories.security;

import java.util.Optional;

import br.eti.deividferreira.legalpecas.domain.entities.security.Authority;
import br.eti.deividferreira.legalpecas.domain.repositories.DefaultRepository;

public interface AuthorityRepository extends DefaultRepository<Authority> {

	Optional<Authority> findByName(String administrator);

}
