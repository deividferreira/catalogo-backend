package br.eti.deividferreira.catalogo.app.controllers.registration;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.eti.deividferreira.catalogo.domain.entities.registration.Parts;
import br.eti.deividferreira.catalogo.domain.repositories.registration.PartsRepository;
import br.eti.deividferreira.catalogo.infra.misc.RestPreconditions;

@RestController
@RequestMapping("/public/parts")
public class PartsPublicController {
	
	private final PartsRepository partsRepository;
		
	/**
	 * @param partsRepository
	 */
	public PartsPublicController(PartsRepository partsRepository) {
		this.partsRepository = partsRepository;
	}

	@GetMapping
    public ResponseEntity<Page<Parts>> get(@RequestParam(required = false) String filter, Pageable pageable) {
        return RestPreconditions.checkFound(this.partsRepository.findByFilter(filter, pageable));
    }

}
