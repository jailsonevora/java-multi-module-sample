package com.ine.sge.dao;

import com.ine.sge.models.Provincia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProvinciaRepository extends PagingAndSortingRepository<Provincia, Long> {

	@Query(value="select sge_provincia.* from sge_provincia\n" +
			"inner join sge_pais on sge_pais.id_pais = sge_provincia.PAIS\n" +
			"where sge_provincia.PAIS = ?1 and sge_provincia.ESTADO = 1 and sge_pais.ESTADO = 1 \n#pageable\n",
			nativeQuery=true)
	Page<Provincia> findProvincesByCountry(Long id, Pageable pageable);

	Optional<Provincia> findByIdAndEstado(long id, int estado);

	Page<Provincia> findAllByEstado(int estado, Pageable pageable);
}
