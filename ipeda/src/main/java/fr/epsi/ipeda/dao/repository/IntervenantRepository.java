package fr.epsi.ipeda.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.epsi.ipeda.dao.entity.Intervenant;

@Repository
public interface IntervenantRepository extends CrudRepository<Intervenant, Long> {

	List<Intervenant> findByNom(String nom);

	Intervenant findByNomAndPrenom(String nom, String prenom);

}
