package fr.epsi.ipeda.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.epsi.ipeda.dao.entity.Formation;
import fr.epsi.ipeda.dao.entity.Semestre;
import fr.epsi.ipeda.dao.entity.Semestre.NumeroSemestre;

@Repository
public interface SemestreRepository extends CrudRepository<Semestre, Long> {

	Semestre findByFormationAndNumeroSemestre(Formation formation, NumeroSemestre numeroSemestre);

}
