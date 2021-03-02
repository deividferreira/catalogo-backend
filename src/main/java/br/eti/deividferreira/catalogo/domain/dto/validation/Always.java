package br.eti.deividferreira.catalogo.domain.dto.validation;

import javax.validation.GroupSequence;

@GroupSequence({Adding.class, Editing.class})
public interface Always { }
