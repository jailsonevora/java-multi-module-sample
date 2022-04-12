package com.ine.universe.dao;

import com.ine.universe.models.Estabelecimento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEstabelecimentoRepository extends PagingAndSortingRepository<Estabelecimento, Long> {

	Optional<Estabelecimento> findByIdAndEstado(long id, int estado);

	Page<Estabelecimento> findAllByEstado(int estado, Pageable pageable);
}
