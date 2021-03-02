package br.eti.deividferreira.catalogo.app.components.security;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

import br.eti.deividferreira.catalogo.domain.entities.security.Authorities;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
@PreAuthorize("hasAuthority('" + Authorities.ADMINISTRATOR + "')")
public @interface Administrator { }
