package com.ine.universe.dao;

import com.ine.universe.models.CAE;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICAERepository extends PagingAndSortingRepository<CAE, Long> {

	Optional<CAE> findByIdAndEstado(long id, int estado);

	Page<CAE> findAllByEstado(int estado, Pageable pageable);
}
