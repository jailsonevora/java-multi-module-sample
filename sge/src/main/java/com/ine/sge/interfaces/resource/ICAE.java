package com.ine.sge.interfaces.resource;

import com.ine.sge.models.CAE;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ICAE {

	ResponseEntity<Page<CAE>> search(@Valid @PathVariable String keyword);

	ResponseEntity<?> show(@Valid @PathVariable Long id);

	ResponseEntity<Page<CAE>> showall(Pageable pageable);

	ResponseEntity<Void> create(@Valid @RequestBody CAE newCae);

	ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody CAE toUpdate);

	ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
