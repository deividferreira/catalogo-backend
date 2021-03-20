package br.eti.deividferreira.catalogo.domain.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eti.deividferreira.catalogo.domain.entities.registration.Parts;
import br.eti.deividferreira.catalogo.domain.repositories.registration.PartsRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class PartsCatalogService {

	private final PartsRepository partsRepository;

	@Transactional
	public Parts save(Parts parts) {
		return this.partsRepository.save(parts);
	}

	@Transactional
	public Parts update(Parts parts) {
		return this.partsRepository.save(parts);
	}

}
