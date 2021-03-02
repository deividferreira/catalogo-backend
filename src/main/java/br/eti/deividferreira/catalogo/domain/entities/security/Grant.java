package br.eti.deividferreira.catalogo.domain.entities.security;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@Table(name = "grants")
public class Grant extends PersistentEntity {
	
	@Getter
    @Setter
    @ManyToOne(optional = false)
    @NotNull(message = "grant.null-authority")
    @JoinColumn(name = "id_authority", nullable = false)
    private Authority authority;
	
    @Getter
    @Setter
    @ManyToOne(optional = false)
    @NotNull(message = "grant.null-user")
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    
    public Grant(User user, Authority authority) {
        this.user = user;
        this.authority = authority;
    }

}
