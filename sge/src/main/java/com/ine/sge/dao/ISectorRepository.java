package com.ine.sge.dao;

import com.ine.sge.models.Sector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISectorRepository extends PagingAndSortingRepository<Sector, Long> {

	Optional<Sector> findByIdAndEstado(long id, int estado);

	Page<Sector> findAllByEstado(int estado, Pageable pageable);
}


