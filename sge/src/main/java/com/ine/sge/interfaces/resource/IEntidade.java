package com.ine.sge.interfaces.resource;

import com.ine.sge.models.Entidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Date;

public interface IEntidade {

	ResponseEntity<Page<com.ine.sge.models.Entidade>> search(@Valid @PathVariable String keyword);

	ResponseEntity<Page<com.ine.sge.models.Entidade>> searchByfield(@Valid @PathVariable String keyword, @Valid @PathVariable String field);

	ResponseEntity<?> show(@Valid @PathVariable Long id);

	ResponseEntity<Page<com.ine.sge.models.Entidade>> showall(Pageable pageable);

	ResponseEntity<Page<com.ine.sge.models.Entidade>> showallIndividualApproved(@Valid Pageable pageable);

	ResponseEntity<Page<com.ine.sge.models.Entidade>> showallIndividualToApprove(@Valid Pageable pageable);

	ResponseEntity<Page<com.ine.sge.models.Entidade>> showallIndividualDeleted(@Valid Pageable pageable);

	ResponseEntity<Page<com.ine.sge.models.Entidade>> showallCollectiveApproved(@Valid Pageable pageable);

	ResponseEntity<Page<com.ine.sge.models.Entidade>> showallCollectiveToApprove(@Valid Pageable pageable);

	ResponseEntity<Page<com.ine.sge.models.Entidade>> showallCollectiveDeleted(@Valid Pageable pageable);

	ResponseEntity<Void> renewEntity(@Valid @PathVariable Long idOfListedEntityBeforeTheApproval,@Valid @RequestBody Entidade newEntity);

	ResponseEntity<Void> approvalEntity(@Valid @PathVariable Long idOfListedEntityBeforeTheApproval);

	ResponseEntity<Void> refuseEntity(@Valid @PathVariable Long idOfListedEntityBeforeTheApproval, @Valid @RequestBody String comment, @Valid @RequestBody Date startDate, @Valid @RequestBody Date endDate);

	ResponseEntity<Void> create(@Valid @RequestBody com.ine.sge.models.Entidade newEntity);

	ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody Entidade toUpdate);

	ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
