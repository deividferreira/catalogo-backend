package br.eti.deividferreira.legalpecas.domain.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

public class BaseForm {

	@Getter
	@Setter
	private Long id;
	@Getter
	@Setter
	private LocalDateTime createdOn;
	@Getter
	@Setter
	private LocalDateTime updatedOn;
}
