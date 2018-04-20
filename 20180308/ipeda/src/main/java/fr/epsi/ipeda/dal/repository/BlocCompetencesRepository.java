package fr.epsi.ipeda.dal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.epsi.ipeda.dal.entity.BlocCompetences;
import fr.epsi.ipeda.dal.entity.Parcours;

public interface BlocCompetencesRepository extends CrudRepository<BlocCompetences, Long> {

	List<BlocCompetences> findByParcours(Parcours parcours);

	BlocCompetences findByParcoursAndNumero(Parcours parcours, int numero);

}
