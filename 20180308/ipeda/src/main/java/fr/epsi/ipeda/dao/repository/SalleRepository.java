package fr.epsi.ipeda.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.epsi.ipeda.dao.entity.Salle;
import fr.epsi.ipeda.dao.entity.Salle.CodeSalle;

@Repository
public interface SalleRepository extends CrudRepository<Salle, Long> {

	List<Salle> findAll();

	Salle findByCodeSalle(CodeSalle codeSalle);

	Salle findByLibelle(String libelle);

}
