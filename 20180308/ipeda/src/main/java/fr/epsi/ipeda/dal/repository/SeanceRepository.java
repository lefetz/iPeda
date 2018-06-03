package fr.epsi.ipeda.dal.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.epsi.ipeda.dal.entity.Planning;
import fr.epsi.ipeda.dal.entity.Seance;

@Repository
public interface SeanceRepository extends CrudRepository<Seance, Long> {

	Seance findByPlanningAndDateAndHeureDebut(Planning planning, LocalDate date, LocalTime heureDebut);

}
