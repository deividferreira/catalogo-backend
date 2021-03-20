package br.eti.deividferreira.catalogo.app.controllers.registration;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.eti.deividferreira.catalogo.app.components.security.Administrator;
import br.eti.deividferreira.catalogo.domain.dto.validation.Adding;
import br.eti.deividferreira.catalogo.domain.dto.validation.Editing;
import br.eti.deividferreira.catalogo.domain.entities.registration.Parts;
import br.eti.deividferreira.catalogo.domain.repositories.registration.PartsRepository;
import br.eti.deividferreira.catalogo.domain.services.PartsCatalogService;
import br.eti.deividferreira.catalogo.infra.misc.RestPreconditions;

@RestController
@RequestMapping("/api/parts")
public class PartsController {
	
	private final PartsRepository partsRepository;
	private final PartsCatalogService catalogService;
	@Autowired
    private ModelMapper modelMapper;
    
    
    @PostConstruct
    protected void configureMapper() {
        this.modelMapper.createTypeMap(Parts.class, Parts.class);
    }
		
	/**
	 * @param partsRepository
	 */
	public PartsController(PartsRepository partsRepository, PartsCatalogService partsService) {
		this.partsRepository = partsRepository;
		this.catalogService = partsService;
	}

	@GetMapping
    @Administrator
    public ResponseEntity<Page<Parts>> get(@RequestParam(required = false) String filter, Pageable pageable) {
        return RestPreconditions.checkFound(this.partsRepository.findByFilter(filter, pageable));
    }
	
	@Administrator
    @GetMapping("/{partsId}")
    public ResponseEntity<Parts> getById(@PathVariable UUID partsId) {
        return this.partsRepository.findById(partsId)
        		.map(found -> ResponseEntity.ok(found))
        		.orElse(ResponseEntity.noContent().build());
    }
	
	@PostMapping
    @Administrator
    public ResponseEntity<Parts> save(@RequestBody @Validated(Adding.class) Parts model) {
        this.catalogService.save(model);

        return ResponseEntity.ok().build();
    }
	
	@Administrator
    @PutMapping("/{partsId}")
    public ResponseEntity<Parts> update(@PathVariable UUID partsId,
                                           @RequestBody @Validated(Editing.class) Parts model) {
        return this.partsRepository.findById(partsId)
                .map(found -> {
                	final var part = this.modelMapper.map(model, Parts.class);
                    final var saved = this.catalogService.update(part);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.badRequest().build());
    }

    @Administrator
    @DeleteMapping("/{partsId}")
    public ResponseEntity<?> delete(@PathVariable UUID partsId) {
        return this.partsRepository.findById(partsId).map(part -> {
            this.partsRepository.delete(part);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.badRequest().build());
    }

}
