package com.ine.universe.interfaces.resource;

import com.ine.universe.models.EmpresaParticipada;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IEmpresaParticipada {

	ResponseEntity<?> show(@Valid @PathVariable Long id);

	ResponseEntity<Page<EmpresaParticipada>> showall(Pageable pageable);

	ResponseEntity<Void> create(@Valid @RequestBody EmpresaParticipada novaEmpresaParticipada);

	ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody EmpresaParticipada empresaParticipadaASerModificada);

	ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
