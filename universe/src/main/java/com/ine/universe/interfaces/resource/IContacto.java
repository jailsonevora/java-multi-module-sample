package com.ine.universe.interfaces.resource;

import com.ine.universe.models.Contacto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IContacto {

	ResponseEntity<Page<Contacto>> search(@Valid @PathVariable String keyword);

	ResponseEntity<?> show(@Valid @PathVariable Long id);

	ResponseEntity<Page<Contacto>> showall(Pageable pageable);

	ResponseEntity<Void> create(@Valid @RequestBody Contacto newContact);

	ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody Contacto toUpdate);

	ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
