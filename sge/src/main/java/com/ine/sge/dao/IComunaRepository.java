package com.ine.sge.dao;

import com.ine.sge.models.Comuna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IComunaRepository extends PagingAndSortingRepository<Comuna, Long> {

	@Query(value="select sge_comuna.* from sge_comuna \n" +
			"inner join sge_municipio on sge_municipio.id_munici = sge_comuna.municipio\n" +
			"where sge_comuna.MUNICIPIO = ?1 and sge_comuna.ESTADO = 1 and sge_municipio.ESTADO = 1 \n#pageable\n",
			nativeQuery=true)
	Page<Comuna> findComunasByCounty(Long id, Pageable pageable);

	Optional<Comuna> findByIdAndEstado(long id, int estado);

	Page<Comuna> findAllByEstado(int estado, Pageable pageable);
}
