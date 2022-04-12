package com.ine.sge.dao;

import com.ine.sge.models.Situacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISituacaoRepository extends PagingAndSortingRepository<Situacao, Long> {

	Optional<Situacao> findByIdAndEstado(long id, int estado);

	Page<Situacao> findAllByEstado(int estado, Pageable pageable);
}
