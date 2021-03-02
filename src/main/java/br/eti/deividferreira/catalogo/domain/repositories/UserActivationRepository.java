package br.eti.deividferreira.catalogo.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.eti.deividferreira.catalogo.domain.entities.security.ActivationStatus;
import br.eti.deividferreira.catalogo.domain.entities.security.UserActivation;

@Repository
public interface UserActivationRepository extends DefaultRepository<UserActivation> {

	Optional<UserActivation> findByTokenAndActivationStatus(String token, ActivationStatus status);

	List<UserActivation> findByActivationStatus(ActivationStatus activationStatus);

	void deleteByUser_id(UUID userId);

}
