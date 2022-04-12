package com.ine.sge.dao;

import com.ine.sge.models.Entidade;
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


	Optional<Entidade> findDistinctTopByOrderByNaturalIdDesc();


	Optional<Entidade> findByNaturalIdAndEstadoAndPorRenovar(String naturalId, int estado, boolean fals);


	Optional<Entidade> findByIdAndEstadoAndPorRenovar(long id, int estado, boolean porRenovar);


	Page<Entidade> findAllByEstado(int estado, Pageable pageable);


	Page<Entidade> findAllByEstadoAndPorRenovar(int estado, boolean porRenovar, Pageable pageable);


	boolean existsEntidadeByNaturalId(String key);


	/*0 = Not Approved, 1 = Approved, tipoEntidade = INDIVIDUAL vs Coletivo*/
	Page<Entidade> findAllByTipoEntidadeTipoLikeAndPorRenovarAndEstadoAprovacaoAndEstado(String tipoEntidade, boolean porrenovar, float estadoaprovacao, int estado, Pageable pageable);


	/*0 = Not Approved, 1 = Approved, tipoEntidade = INDIVIDUAL vs Coletivo*/
	Page<Entidade> findAllByEstadoAprovacaoAndTipoEntidadeTipoLikeAndEstado(float valueEstadoAprovacao, String tipoEntidade, int estado, Pageable pageable);

	/*0 = SoftDeleted, 1 = NotDeleted, tipoEntidade = INDIVIDUAL vs Coletivo*/
	Page<Entidade> findAllByEstadoAndTipoEntidadeTipoLike(int valueEstado, String tipoEntidade, Pageable pageable);


	Page<?> findAllByUpdatedAtBetweenAndEstadoAndTipoEntidade_IdOrderByUpdatedAtDesc(Date startdate, Date enddate, int estado, Long tipoentidadeID, Pageable pageable);


	Page<?> findAllByEstadoAndUpdatedAtBetweenAndTipoEntidade_IdOrderByUpdatedAtDesc(int estadoEliminado,  Date startdate, Date enddate, Long tipoentidadeID, Pageable pageable);


	@Query(value="Select count(sge_entidade.id_entidade) as 'TotalEntidadeByProvincia', sge_provincia.designacao\n" +
			"from sge_entidade\n" +
			"inner join recdif.sge_contacto on sge_contacto.id_contacto = sge_entidade.contacto\n" +
			"inner join recdif.sge_provincia on sge_provincia.id_provin = sge_contacto.provincia\n" +
			"inner join recdif.sge_tipoentidade on sge_entidade.tipoEntidade = sge_tipoentidade.id_tipo_entidade\n" +
			"where sge_entidade.created_at >= ?1 and sge_entidade.created_at <= ?2 and sge_tipoentidade.id_tipo_entidade = ?3 \n" +
			"group by sge_provincia.designacao, 'TotalEntidadeByProvincia' \n#pageable\n",
			nativeQuery=true)
	Page<?> findAllByEntidadesPorProvincias(Date startdate, Date enddate, Long tipoentidadeID, Pageable pageable);


	@Query(value="Select YEAR(sge_entidade.created_at) as 'Year', AVG(sge_entidade.num_funcionario_homem) as 'MediaHomens', AVG(sge_entidade.num_funcionario_mulher) as 'MediaMulheres'\n" +
			"from sge_entidade\n" +
			"inner join recdif.sge_tipoentidade on sge_entidade.tipoEntidade = sge_tipoentidade.id_tipo_entidade\n" +
			"where sge_entidade.created_at >= ?1 and sge_entidade.created_at <= ?2 and sge_tipoentidade.id_tipo_entidade = ?3 \n" +
			"group by  YEAR(sge_entidade.created_at), 'MediaHomens', 'MediaMulheres' \n#pageable\n",
			nativeQuery=true)
	Page<?> avgAllByNumFuncionarioHomemBetween(Date startdate, Date enddate, Long tipoentidadeID, Pageable pageable);
}
