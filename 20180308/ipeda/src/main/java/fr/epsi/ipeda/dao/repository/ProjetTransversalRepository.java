package fr.epsi.ipeda.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.epsi.ipeda.dao.entity.BlocCompetences;
import fr.epsi.ipeda.dao.entity.ProjetTransversal;

public interface ProjetTransversalRepository extends CrudRepository<ProjetTransversal, String> {

	List<ProjetTransversal> findByBlocCompetences(BlocCompetences blocCompetences);

	ProjetTransversal findByCode(String code);

}
