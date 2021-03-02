package br.eti.deividferreira.legalpecas.app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.eti.deividferreira.legalpecas.domain.dto.RegistrationForm;
import br.eti.deividferreira.legalpecas.domain.services.UserAccountService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/user-account")
public class UserAccountController {
	
	private final UserAccountService userService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Validated RegistrationForm registrationForm) {
		this.userService.register(registrationForm);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/activate/{token}")
    public ResponseEntity<?> activate(@PathVariable String token) {
        this.userService.activate(token);
        return ResponseEntity.ok().build();
    }
	
	@GetMapping("/recover-password/{email}")
    public ResponseEntity<?> recoverPassword(@PathVariable String email) {
        this.userService.recoverPassword(email);
        return ResponseEntity.ok().build();
    }

}
