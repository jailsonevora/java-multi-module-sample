package com.ine.sge.interfaces.resource;

import com.ine.sge.models.Mensagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IMensagem {

	ResponseEntity<Page<Mensagem>> search(@Valid @PathVariable String keyword);

	ResponseEntity<?> show(@Valid @PathVariable Long id);

	ResponseEntity<Page<Mensagem>> showall(Pageable pageable);

	ResponseEntity<Page<Mensagem>> showallNew(Pageable pageable);

	ResponseEntity<Void> create(@Valid @RequestBody Mensagem newMessage);

	ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody Mensagem toUpdate);

	ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
