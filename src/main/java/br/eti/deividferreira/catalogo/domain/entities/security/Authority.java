package br.eti.deividferreira.catalogo.domain.entities.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import br.eti.deividferreira.catalogo.domain.entities.PersistentEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "authorities")
public class Authority extends PersistentEntity {

    @Getter
    @Setter
    @NotBlank(message = "authority.empty-name")
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    
    public Authority(String name) {
        this.name = name;
    }
}
