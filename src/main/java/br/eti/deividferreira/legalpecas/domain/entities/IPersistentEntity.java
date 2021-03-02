package br.eti.deividferreira.legalpecas.domain.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IPersistentEntity<T extends Serializable> extends Serializable {
	
	T getId();

    @JsonIgnore
    boolean isSaved();

    @JsonIgnore
    default boolean isNotSaved() {
        return !this.isSaved();
    }

}
