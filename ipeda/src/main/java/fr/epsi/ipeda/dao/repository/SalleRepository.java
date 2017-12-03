package fr.epsi.ipeda.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.epsi.ipeda.dao.entity.Salle;

@Repository
public interface SalleRepository extends CrudRepository<Salle, Long> {

	List<Salle> findByLibelle(String libelle);

}
