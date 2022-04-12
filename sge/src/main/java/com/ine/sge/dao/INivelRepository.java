package com.ine.sge.dao;

import com.ine.sge.models.Nivel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface INivelRepository extends PagingAndSortingRepository<Nivel, Long> {

	Optional<Nivel> findByIdAndEstado(long id, int estado);

	Page<Nivel> findAllByEstado(int estado, Pageable pageable);
}
