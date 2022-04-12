package com.ine.sge.interfaces.resource;

import com.ine.sge.models.Comuna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IComuna {

	ResponseEntity<Page<Comuna>> search(@Valid @PathVariable String keyword);

	ResponseEntity<?> show(@Valid @PathVariable Long id);

	ResponseEntity<Page<Comuna>> showall(Pageable pageable);

	ResponseEntity<Page<Comuna>> showallbyprovince(@Valid @PathVariable Long municipioid, @Valid Pageable pageable);

	ResponseEntity<Void> create(@Valid @PathVariable Long municipioid, @Valid @RequestBody Comuna newComuna);

	ResponseEntity<Void> update(@Valid @PathVariable Long municipioid, @Valid @PathVariable Long id, @Valid @RequestBody Comuna toUpdate);

	ResponseEntity<Void> delete(@Valid @PathVariable Long municipioid, @Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
