package com.ine.sge.interfaces.resource;

import com.ine.sge.models.Aldeia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IAldeia {

	ResponseEntity<Page<Aldeia>> search(@Valid @PathVariable String keyword);

	ResponseEntity<?> show(@Valid @PathVariable Long id);

	ResponseEntity<Page<Aldeia>> showall(Pageable pageable);

	ResponseEntity<Page<Aldeia>> showallbycomuna(@Valid @PathVariable Long comunaid, @Valid Pageable pageable);

	ResponseEntity<Void> create(@Valid @PathVariable Long comunaid, @Valid @RequestBody Aldeia newAldeia);

	ResponseEntity<Void> update(@Valid @PathVariable Long comunaid, @Valid @PathVariable Long id, @Valid @RequestBody Aldeia toUpdate);

	ResponseEntity<Void> delete(@Valid @PathVariable Long comunaid, @Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
