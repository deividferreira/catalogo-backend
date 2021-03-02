package br.eti.deividferreira.legalpecas.domain.entities.security;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.eti.deividferreira.legalpecas.domain.entities.PersistentEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.utility.RandomString;

@Entity
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "user_activations")
public class UserActivation extends PersistentEntity {
	
	@Getter
    @Setter
    @Column(name = "token", nullable = false, length = 20, unique = true)
    private String token;
    @Getter
    @Setter
    @Column(name = "expire_in", nullable = false)
    private LocalDateTime expireIn;
    @Getter
    @Setter
    @Column(name = "activated_on")
    private LocalDateTime activatedOn;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "activation_status", nullable = false, length = 20)
    private ActivationStatus activationStatus;

    @Getter
    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
    
    public UserActivation(User user) {
        this.user = user;
        this.expireIn = LocalDateTime.now().plusDays(2);
        this.token = new RandomString(20).nextString();
        this.activationStatus = ActivationStatus.WAITING;
    }

    public UserActivation expire() {
        this.activationStatus = ActivationStatus.EXPIRED;
        return this;
    }

    public UserActivation activate() {
        this.activationStatus = ActivationStatus.ACTIVATED;
        this.activatedOn = LocalDateTime.now();
        return this;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expireIn);
    }

}
