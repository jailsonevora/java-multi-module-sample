package com.ine.sge.dao;

import com.ine.sge.models.TipoEntidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITipoEntidadeRepository extends PagingAndSortingRepository<TipoEntidade, Long> {

	Optional<TipoEntidade> findByIdAndEstado(long id, int estado);

	Page<TipoEntidade> findAllByEstado(int estado, Pageable pageable);
}