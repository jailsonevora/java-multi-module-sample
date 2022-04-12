package com.ine.sge.interfaces.resource;

import com.ine.sge.models.Municipio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IMunicipio {

	ResponseEntity<Page<Municipio>> search(@Valid @PathVariable String keyword);

	ResponseEntity<?> show(@Valid @PathVariable Long municipioid);

	ResponseEntity<Page<Municipio>> showall(Pageable pageable);

	ResponseEntity<Page<Municipio>> showallbyprovince(@Valid @PathVariable Long provinciaid, @Valid Pageable pageable);

	ResponseEntity<Void> create(@Valid @PathVariable Long provinciaid, @Valid @RequestBody Municipio newCounty);

	ResponseEntity<Void> update(@Valid @PathVariable Long provinciaid, @Valid @PathVariable Long id, @Valid @RequestBody Municipio toUpdate);

	ResponseEntity<Void> delete(@Valid @PathVariable Long provinciaid, @Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
