package com.ine.sge.dao;

import com.ine.sge.models.FormaJuridica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFormaJuridicaRepository extends PagingAndSortingRepository<FormaJuridica, Long> {

	Optional<FormaJuridica> findByIdAndEstado(long id, int estado);

	Page<FormaJuridica> findAllByEstado(int estado, Pageable pageable);
}
