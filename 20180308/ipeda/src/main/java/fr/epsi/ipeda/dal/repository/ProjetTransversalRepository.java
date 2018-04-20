package fr.epsi.ipeda.dal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.epsi.ipeda.dal.entity.BlocCompetences;
import fr.epsi.ipeda.dal.entity.ProjetTransversal;

public interface ProjetTransversalRepository extends CrudRepository<ProjetTransversal, String> {

	List<ProjetTransversal> findByBlocCompetences(BlocCompetences blocCompetences);

	ProjetTransversal findByCode(String code);

}
