package br.eti.deividferreira.legalpecas.domain.entities.security;

import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.EAGER;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.eti.deividferreira.legalpecas.domain.entities.PersistentEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@ToString(exclude = "grants")
@EqualsAndHashCode(callSuper = true, exclude = "grants")
public class User extends PersistentEntity {

	@Getter
	@Setter
	@Column(name = "name", nullable = false, length = 90)
	private String name;
	@Getter
    @Setter
    @Column(name = "username", nullable = false, length = 45, unique = true)
    private String username;
	@Getter
	@Setter
	@Column(name = "last_name", nullable = false, length = 155)
	private String lastName;
	@Getter
	@Setter
	@Column(name = "email", nullable = false, length = 90, unique = true)
	private String email;
	@Getter
	@Setter
	@JsonIgnore
	@Column(name = "password", nullable = false)
	private String password;
	@Getter
	@Setter
	@Column(name = "active", nullable = false)
	private boolean active;

	@Setter
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = EAGER, cascade = { REMOVE })
	private List<Grant> grants;

	public User() {
		this.active = false;
		this.grants = new ArrayList<>();
	}

	public User enable() {
		this.active = true;
		return this;
	}

	public List<Grant> getGrants() {
		return Collections.unmodifiableList(this.grants);
	}

}
