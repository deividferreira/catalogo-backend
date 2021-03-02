package br.eti.deividferreira.catalogo.domain.entities.security;

import java.util.List;

public interface Authorities {
	
    String ADMINISTRATOR = "ADMINISTRATOR";

    List<String> ALL_AUTHORITIES = List.of(ADMINISTRATOR);

}
