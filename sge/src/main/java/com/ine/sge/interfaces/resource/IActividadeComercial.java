package com.ine.sge.interfaces.resource;

import com.ine.sge.models.ActividadeComercial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IActividadeComercial {

	ResponseEntity<?> show(@Valid @PathVariable Long id);

	ResponseEntity<Page<ActividadeComercial>> showall(Pageable pageable);

	ResponseEntity<Void> create(@Valid @RequestBody ActividadeComercial newActivity);

	ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody ActividadeComercial toUpdate);

	ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
