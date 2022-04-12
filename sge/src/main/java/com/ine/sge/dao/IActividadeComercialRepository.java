package com.ine.sge.dao;

import com.ine.sge.models.ActividadeComercial;
import com.ine.sge.models.Entidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IActividadeComercialRepository extends PagingAndSortingRepository<ActividadeComercial, Long> {

	Optional<ActividadeComercial> findByIdAndEstado(long id, int estado);

	Page<ActividadeComercial> findAllByEstado(int estado, Pageable pageable);
}