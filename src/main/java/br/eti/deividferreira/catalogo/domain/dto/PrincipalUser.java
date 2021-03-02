package br.eti.deividferreira.catalogo.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.eti.deividferreira.catalogo.domain.entities.security.Authority;
import br.eti.deividferreira.catalogo.domain.entities.security.Grant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class PrincipalUser {

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String username;

    @Getter
    private List<String> authorities;

    public void setGrants(List<Grant> grants) {
        this.authorities = grants.stream()
                .map(Grant::getAuthority)
                .map(Authority::getName)
                .collect(Collectors.toList());
    }
}
