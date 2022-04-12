package com.ine.sge.dao;

import com.ine.sge.models.OrigemDocumento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrigemDocumentoRepository extends PagingAndSortingRepository<OrigemDocumento, Long> {

	Optional<OrigemDocumento> findByIdAndEstado(long id, int estado);

	Page<OrigemDocumento> findAllByEstado(int estado, Pageable pageable);
}
