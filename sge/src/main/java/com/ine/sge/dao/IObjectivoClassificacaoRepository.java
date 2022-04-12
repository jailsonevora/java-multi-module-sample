package com.ine.sge.dao;

import com.ine.sge.models.ObjectivoClassificacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IObjectivoClassificacaoRepository extends PagingAndSortingRepository<ObjectivoClassificacao, Long> {

	Optional<ObjectivoClassificacao> findByIdAndEstado(long id, int estado);

	Page<ObjectivoClassificacao> findAllByEstado(int estado, Pageable pageable);
}
