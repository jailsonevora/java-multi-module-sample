package com.ine.sge.interfaces.resource;

import com.ine.sge.models.Socio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ISocio {

	ResponseEntity<Page<Socio>> search(@Valid @PathVariable String keyword);

	ResponseEntity<?> show(@Valid @PathVariable Long id);

	ResponseEntity<Page<Socio>> showall(Pageable pageable);

	ResponseEntity<Void> create(@Valid @RequestBody Socio newPartner);

	ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody Socio toUpdate);

	ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
