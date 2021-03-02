package br.eti.deividferreira.legalpecas.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class RegistrationForm {
	@Getter
	@Setter
	@NotBlank(message = "registration.empty-name")
	private String name;
	@Getter
	@Setter
	@NotBlank(message = "registration.empty-username")
	private String username;
	@Getter
	@Setter
	@Length(min = 2)
	@NotBlank(message = "registration.empty-lastName")
	private String lastName;
	@Getter
	@Setter
	@NotBlank(message = "registration.empty-password")
	private String password;
	@Getter
	@Setter
	@NotBlank(message = "registration.empty-email")
	@Email(message = "registration.invalid-email")
	private String email;
}
