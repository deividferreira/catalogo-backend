package br.eti.deividferreira.catalogo.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import br.eti.deividferreira.catalogo.domain.dto.validation.Adding;
import br.eti.deividferreira.catalogo.domain.dto.validation.Always;
import br.eti.deividferreira.catalogo.domain.entities.security.Authority;
import br.eti.deividferreira.catalogo.domain.entities.security.Grant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class UserForm extends BaseForm {

	@Getter
	@Setter
	private boolean active;
	@Getter
	@Setter
	@NotBlank(message = "user.empty-name", groups = Always.class)
	private String name;
	@Getter
	@Setter
	@NotBlank(message = "user.empty-username", groups = Always.class)
	private String username;
	@Getter
	@Setter
	@NotBlank(message = "user.empty-password", groups = Adding.class)
	private String password;
	@Getter
	@Setter
	@NotBlank(message = "user.empty-email", groups = Always.class)
	private String email;

	@Getter
	@Setter
	@NotEmpty(message = "user.empty-authorities", groups = Always.class)
	private List<Authority> authorities;


	public UserForm() {
		this.authorities = new ArrayList<>();
	}

	public void setGrants(List<Grant> grants) {
		this.authorities = grants.stream().map(Grant::getAuthority).collect(Collectors.toList());
	}

}
