package com.ine.sge.interfaces.resource;

import com.ine.sge.models.OrigemDocumento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IOrigemDocumento {

	ResponseEntity<Page<OrigemDocumento>> search(@Valid @PathVariable String keyword);

	ResponseEntity<?> show(@Valid @PathVariable Long id);

	ResponseEntity<Page<OrigemDocumento>> showall(Pageable pageable);

	ResponseEntity<Void> create(@Valid @RequestBody OrigemDocumento newSource);

	ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody OrigemDocumento toUpdate);

	ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
