package fr.epsi.ipeda.dal.repository.periode;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;

import fr.epsi.ipeda.dal.entity.periode.Periode;

public interface PeriodeRepositoryCustom {

	// @formatter:off
	@Query(value = "select p.* from periode p "
			+ "inner join periode_type pt on pt.id = p.periodetype_id "
			+ "where ?1 >= p.dateheuredebut and ?2 <= p.dateheurefin "
			+ "or (p.dateheurefin is null and ?1 >= date_trunc('day', p.dateheuredebut) and ?1 < date_trunc('day', p.dateheuredebut + interval '1 day')) "
			+ "order by pt.priorite "
			+ "LIMIT 1", nativeQuery = true)
	// @formatter:on
	Periode findByDatetime(LocalDateTime dateTimeDebut, LocalDateTime dateTimeFin);
	
	// @formatter:off
	@Query(value = "select p.* from periode p "
			+ "inner join periode_type pt on pt.id = p.periodetype_id "
			+ "order by pt.priorite "
			+ "LIMIT 1", nativeQuery = true)
	// @formatter:on
	Periode testbidule();


}
