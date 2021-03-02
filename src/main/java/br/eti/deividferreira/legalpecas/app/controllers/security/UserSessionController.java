package br.eti.deividferreira.legalpecas.app.controllers.security;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.eti.deividferreira.legalpecas.app.components.AuthenticatedUser;
import br.eti.deividferreira.legalpecas.domain.dto.PrincipalUser;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/me")
public class UserSessionController {
	
	private final ModelMapper modelMapper;
	private final AuthenticatedUser authenticatedUser;
	
	@GetMapping
    public ResponseEntity<PrincipalUser> me() {
        return this.authenticatedUser.get()
                .map(user -> ResponseEntity.ok(this.modelMapper.map(user, PrincipalUser.class)))
                .orElse(ResponseEntity.noContent().build());
    }

}
