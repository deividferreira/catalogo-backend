package br.eti.deividferreira.legalpecas.domain.entities;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@MappedSuperclass
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "createdOn", "updatedOn" })
public abstract class PersistentEntity implements IPersistentEntity<UUID> {

	@Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, updatable = false)
    private UUID id;

	@Getter
	@Column(name = "created_on", nullable = false)
	private LocalDateTime createdOn;
	@Getter
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

	@PrePersist
	protected void beforeInsert() {
		this.createdOn = LocalDateTime.now();
	}

	@PreUpdate
	protected void beforeUpdate() {
		this.updatedOn = LocalDateTime.now();
	}

	@Override
	public boolean isSaved() {
		return Objects.nonNull(this.id);
	}

}
