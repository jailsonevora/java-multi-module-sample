package com.ine.sge.dao;

import com.ine.sge.models.TituloAcademico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITituloAcademicoRepository extends PagingAndSortingRepository<TituloAcademico, Long> {

	Optional<TituloAcademico> findByIdAndEstado(long id, int estado);

	Page<TituloAcademico> findAllByEstado(int estado, Pageable pageable);
}
