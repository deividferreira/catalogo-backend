package br.eti.deividferreira.legalpecas.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.eti.deividferreira.legalpecas.domain.entities.security.ActivationStatus;
import br.eti.deividferreira.legalpecas.domain.entities.security.UserActivation;

@Repository
public interface UserActivationRepository extends DefaultRepository<UserActivation> {

	Optional<UserActivation> findByTokenAndActivationStatus(String token, ActivationStatus status);

	List<UserActivation> findByActivationStatus(ActivationStatus activationStatus);

	void deleteByUser_id(UUID userId);

}
