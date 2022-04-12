package com.ine.sge.interfaces.resource;

import com.ine.sge.models.Contabilidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IContabilidade {

	ResponseEntity<Page<Contabilidade>> search(@Valid @PathVariable String keyword);

	ResponseEntity<?> show(@Valid @PathVariable Long id);

	ResponseEntity<Page<Contabilidade>> showall(Pageable pageable);

	ResponseEntity<Void> create(@Valid @RequestBody Contabilidade newAccounting);

	ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody Contabilidade toUpdate);

	ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
