package com.ine.universe.interfaces.resource;

import com.ine.universe.models.Entidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IEntidade {

	ResponseEntity<Page<com.ine.universe.models.Entidade>> search(@Valid @PathVariable String keyword);

	ResponseEntity<?> show(@Valid @PathVariable Long id);

	ResponseEntity<Page<com.ine.universe.models.Entidade>> showall(Pageable pageable);

	ResponseEntity<Void> create(@Valid @RequestBody com.ine.universe.models.Entidade newEntity);

	ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody Entidade toUpdate);

	ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
