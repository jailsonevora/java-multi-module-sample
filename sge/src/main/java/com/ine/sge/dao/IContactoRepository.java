package com.ine.sge.dao;

import com.ine.sge.models.Contacto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IContactoRepository extends PagingAndSortingRepository<Contacto, Long> {

	Optional<Contacto> findByIdAndEstado(long id, int estado);

	Page<Contacto> findAllByEstado(int estado, Pageable pageable);
}
