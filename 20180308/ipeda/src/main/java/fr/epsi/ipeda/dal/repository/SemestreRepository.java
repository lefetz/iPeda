package fr.epsi.ipeda.dal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.epsi.ipeda.dal.entity.Formation;
import fr.epsi.ipeda.dal.entity.Semestre;
import fr.epsi.ipeda.dal.entity.Semestre.NumeroSemestre;

@Repository
public interface SemestreRepository extends CrudRepository<Semestre, Long> {

	Semestre findByFormationAndNumeroSemestre(Formation formation, NumeroSemestre numeroSemestre);

}
