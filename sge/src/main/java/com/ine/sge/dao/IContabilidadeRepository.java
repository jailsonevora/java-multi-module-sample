package com.ine.sge.dao;

import com.ine.sge.models.Contabilidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IContabilidadeRepository extends PagingAndSortingRepository<Contabilidade, Long> {

	Optional<Contabilidade> findByIdAndEstado(long id, int estado);

	Page<Contabilidade> findAllByEstado(int estado, Pageable pageable);
}
