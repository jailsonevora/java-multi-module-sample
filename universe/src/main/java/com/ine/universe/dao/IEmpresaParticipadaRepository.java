package com.ine.universe.dao;

import com.ine.universe.models.EmpresaParticipada;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmpresaParticipadaRepository extends PagingAndSortingRepository<EmpresaParticipada, Long> {

	Optional<EmpresaParticipada> findByIdAndEstado(long id, int estado);

	Page<EmpresaParticipada> findAllByEstado(int estado, Pageable pageable);
}
