package com.ine.sge.interfaces.resource;

import com.ine.sge.models.TituloAcademico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ITituloAcademico {

	ResponseEntity<?> show(@Valid @PathVariable Long id);

	ResponseEntity<Page<TituloAcademico>> showall(Pageable pageable);

	ResponseEntity<Void> create(@Valid @RequestBody TituloAcademico newDegree);

	ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody TituloAcademico toUpdate);

	ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
