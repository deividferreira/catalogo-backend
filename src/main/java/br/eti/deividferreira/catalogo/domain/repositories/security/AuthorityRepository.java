package br.eti.deividferreira.catalogo.domain.repositories.security;

import java.util.Optional;

import br.eti.deividferreira.catalogo.domain.entities.security.Authority;
import br.eti.deividferreira.catalogo.domain.repositories.DefaultRepository;

public interface AuthorityRepository extends DefaultRepository<Authority> {

	Optional<Authority> findByName(String administrator);

}
