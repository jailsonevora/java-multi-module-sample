package com.ine.sge.dao;

import com.ine.sge.models.Pais;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPaisRepository extends PagingAndSortingRepository<Pais, Long> {

	Optional<Pais> findByIdAndEstado(long id, int estado);

	Page<Pais> findAllByEstado(int estado, Pageable pageable);
}
