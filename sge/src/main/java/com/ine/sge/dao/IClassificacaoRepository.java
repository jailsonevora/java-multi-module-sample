package com.ine.sge.dao;

import com.ine.sge.models.Classificacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClassificacaoRepository extends PagingAndSortingRepository<Classificacao, Long> {

	Optional<Classificacao> findByIdAndEstado(long id, int estado);

	Page<Classificacao> findAllByEstado(int estado, Pageable pageable);
}
