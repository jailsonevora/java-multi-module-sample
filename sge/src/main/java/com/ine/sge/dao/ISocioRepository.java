package com.ine.sge.dao;

import com.ine.sge.models.Socio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISocioRepository extends PagingAndSortingRepository<Socio, Long> {

	Optional<Socio> findByIdAndEstado(long id, int estado);

	Page<Socio> findAllByEstado(int estado, Pageable pageable);
}
