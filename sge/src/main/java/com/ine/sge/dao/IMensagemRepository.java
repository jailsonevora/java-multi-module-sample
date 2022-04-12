package com.ine.sge.dao;

import com.ine.sge.models.Mensagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMensagemRepository extends PagingAndSortingRepository<Mensagem, Long> {

	Page<Mensagem> findAllByLidaAndEstado(boolean lida, int estado, Pageable pageable);

	Optional<Mensagem> findByIdAndEstado(long id, int estado);

	Page<Mensagem> findAllByEstado(int estado, Pageable pageable);
}
