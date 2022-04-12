package com.ine.sge.interfaces.resource;

import com.ine.sge.models.Provincia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IProvincia {

	ResponseEntity<Page<Provincia>> search(@Valid @PathVariable String keyword);

	ResponseEntity<?> show(@Valid @PathVariable Long provinciaid);

	ResponseEntity<Page<Provincia>> showall(Pageable pageable);

	ResponseEntity<Page<Provincia>> showallbycountry(@Valid @PathVariable Long paisid, @Valid Pageable pageable);

	ResponseEntity<Void> create(@Valid @PathVariable Long paisid, @Valid @RequestBody Provincia newProvince);

	ResponseEntity<Void> update(@Valid @PathVariable Long paisid, @Valid @PathVariable Long provinciaid, @Valid @RequestBody Provincia toUpdate);

	ResponseEntity<Void> delete(@Valid @PathVariable Long paisid, @Valid @PathVariable Long provinciaid, @Valid @RequestBody String lastModifiedBy);
}
