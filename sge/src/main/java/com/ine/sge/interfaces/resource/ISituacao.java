package com.ine.sge.interfaces.resource;

import com.ine.sge.models.Situacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ISituacao {

	ResponseEntity<?> show(@Valid @PathVariable Long id);

	ResponseEntity<Page<Situacao>> showall(Pageable pageable);

	ResponseEntity<Void> create(@Valid @RequestBody Situacao newPosition);

	ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody Situacao toUpdate);

	ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
