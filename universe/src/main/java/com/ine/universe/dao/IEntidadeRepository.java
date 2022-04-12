package com.ine.universe.dao;

import com.ine.universe.models.Entidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface IEntidadeRepository extends PagingAndSortingRepository<Entidade, Long> {

	Optional<Entidade> findByIdAndEstado(long id, int estado);

	Optional<Entidade> findByNaturalIdAndEstadoAndPorRenovar(String naturalId, int estado, boolean fals);


	Optional<Entidade> findByIdAndEstadoAndPorRenovar(long id, int estado, boolean porRenovar);


	Page<Entidade> findAllByEstado(int estado, Pageable pageable);


	Page<Entidade> findAllByEstadoAndPorRenovar(int estado, boolean porRenovar, Pageable pageable);


	boolean existsEntidadeByNaturalId(String key);


	/*0 = Not Approved, 1 = Approved, tipoEntidade = INDIVIDUAL vs Coletivo*/
	Page<Entidade> findAllByTipoEntidadeLikeAndPorRenovarAndEstadoAprovacaoAndEstado(String tipoEntidade, boolean porrenovar, float estadoaprovacao, int estado, Pageable pageable);


	/*0 = Not Approved, 1 = Approved, tipoEntidade = INDIVIDUAL vs Coletivo*/
	Page<Entidade> findAllByEstadoAprovacaoAndTipoEntidadeLikeAndEstado(float valueEstadoAprovacao, String tipoEntidade, int estado, Pageable pageable);

	/*0 = SoftDeleted, 1 = NotDeleted, tipoEntidade = INDIVIDUAL vs Coletivo*/
	Page<Entidade> findAllByEstadoAndTipoEntidadeLike(int valueEstado, String tipoEntidade, Pageable pageable);


	Page<?> findAllByUpdatedAtBetweenAndEstadoAndTipoEntidadeOrderByUpdatedAtDesc(Date startdate, Date enddate, int estado, String tipoentidade, Pageable pageable);


	Page<?> findAllByEstadoAndUpdatedAtBetweenAndTipoEntidadeOrderByUpdatedAtDesc(int estadoEliminado,  Date startdate, Date enddate, String tipoentidade, Pageable pageable);
}
