package com.ine.sge.dao;

import com.ine.sge.models.Aldeia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IAldeiaRepository extends PagingAndSortingRepository<Aldeia, Long> {

	@Query(value="select sge_aldeia.* from sge_aldeia \n" +
			"inner join sge_comuna on sge_comuna.ID_COMUNA = sge_aldeia.COMUNA\n" +
			"where sge_aldeia.COMUNA = ?1 and sge_aldeia.ESTADO = 1 and sge_COMUNA.ESTADO = 1 \n#pageable\n",
			nativeQuery=true)
	Page<Aldeia> findAldeiaByComuna(Long id, Pageable pageable);

	Optional<Aldeia> findByIdAndEstado(long id, int estado);

	Page<Aldeia> findAllByEstado(int estado, Pageable pageable);
}
