package fr.epsi.ipeda.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.epsi.ipeda.dao.entity.Intervenant;

@Repository
public interface IntervenantRepository extends CrudRepository<Intervenant, Long> {

	Intervenant findByNom(String nom);

	Intervenant findByNomAndPrenom(String nom, String prenom);

}
