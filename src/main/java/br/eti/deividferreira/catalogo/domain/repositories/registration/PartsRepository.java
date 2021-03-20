package br.eti.deividferreira.catalogo.domain.repositories.registration;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import br.eti.deividferreira.catalogo.domain.entities.registration.Parts;
import br.eti.deividferreira.catalogo.domain.repositories.DefaultRepository;

public interface PartsRepository extends DefaultRepository<Parts> {

	@Query("from Parts p where filter(:filter, p.code, p.manufacturerCode,"
			+ " p.manufacturerName, p.description, p.ncm, p.ean, p.brand) = true")
    Page<Parts> findByFilter(String filter, Pageable pageable);

}
