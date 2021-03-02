package br.eti.deividferreira.legalpecas.domain.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eti.deividferreira.legalpecas.domain.entities.PersistentEntity;

public interface DefaultRepository<T extends PersistentEntity> extends JpaRepository<T, UUID> {

}
