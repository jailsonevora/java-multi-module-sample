package com.ine.sge.dao;

import com.ine.sge.models.Canal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICanalRepository extends PagingAndSortingRepository<Canal, Long> {

	Optional<Canal> findByIdAndEstado(long id, int estado);

	Page<Canal> findAllByEstado(int estado, Pageable pageable);
}
