package com.ine.sge.dao;

import com.ine.sge.models.Genero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IGeneroRepository extends PagingAndSortingRepository<Genero, Long> {

	Optional<Genero> findByIdAndEstado(long id, int estado);

	Page<Genero> findAllByEstado(int estado, Pageable pageable);
}
