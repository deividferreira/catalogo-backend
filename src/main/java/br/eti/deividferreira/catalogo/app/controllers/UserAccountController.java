package br.eti.deividferreira.catalogo.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/user-account")
public class UserAccountController {
	
//	private final UserAccountService userService;
//	
//	@PostMapping("/register")
//	public ResponseEntity<?> register(@RequestBody @Validated RegistrationForm registrationForm) {
//		this.userService.register(registrationForm);
//		return ResponseEntity.ok().build();
//	}
//	
//	@GetMapping("/activate/{token}")
//    public ResponseEntity<?> activate(@PathVariable String token) {
//        this.userService.activate(token);
//        return ResponseEntity.ok().build();
//    }
//	
//	@GetMapping("/recover-password/{email}")
//    public ResponseEntity<?> recoverPassword(@PathVariable String email) {
//        this.userService.recoverPassword(email);
//        return ResponseEntity.ok().build();
//    }

}
