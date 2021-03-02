package br.eti.deividferreira.catalogo.app.controllers.security;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.eti.deividferreira.catalogo.domain.entities.security.Authority;
import br.eti.deividferreira.catalogo.domain.repositories.security.AuthorityRepository;
import br.eti.deividferreira.catalogo.infra.misc.RestPreconditions;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authorities")
public class AuthorityController {
	
	private final AuthorityRepository authorityRepository;
	
	@GetMapping
    public ResponseEntity<List<Authority>> get() {
        return RestPreconditions.checkFound(this.authorityRepository.findAll());
    }

}
