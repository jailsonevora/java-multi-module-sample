package com.ine.universe.dao;

import com.ine.universe.models.Seccao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISeccaoRepository extends PagingAndSortingRepository<Seccao, Long> {

	Optional<Seccao> findByIdAndEstado(long id, int estado);

	Page<Seccao> findAllByEstado(int estado, Pageable pageable);
}
