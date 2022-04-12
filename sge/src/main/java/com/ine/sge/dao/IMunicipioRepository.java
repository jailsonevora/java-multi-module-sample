package com.ine.sge.dao;

import com.ine.sge.models.Municipio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMunicipioRepository extends PagingAndSortingRepository<Municipio, Long> {

	@Query(value="select sge_municipio.* from sge_municipio\n" +
			"inner join sge_provincia on sge_provincia.id_provin = sge_municipio.PROVINCIA\n" +
			"where sge_municipio.PROVINCIA = ?1 and sge_municipio. ESTADO = 1 and sge_provincia.ESTADO = 1 \n#pageable\n",
			nativeQuery=true)
	Page<Municipio> findCountiesByProvince(Long id, Pageable pageable);

	Optional<Municipio> findByIdAndEstado(long id, int estado);

	Page<Municipio> findAllByEstado(int estado, Pageable pageable);
}
