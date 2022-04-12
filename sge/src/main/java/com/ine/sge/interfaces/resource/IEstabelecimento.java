package com.ine.sge.interfaces.resource;

import com.ine.sge.models.Estabelecimento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IEstabelecimento {

	ResponseEntity<Page<Estabelecimento>> search(@Valid @PathVariable String keyword);

	ResponseEntity<?> show(@Valid @PathVariable Long id);

	ResponseEntity<Page<Estabelecimento>> showall(Pageable pageable);

	ResponseEntity<Void> create(@Valid @RequestBody Estabelecimento newEstablishment);

	ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody Estabelecimento toUpdate);

	ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);

}
