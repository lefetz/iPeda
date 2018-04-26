package fr.epsi.ipeda.dal.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.epsi.ipeda.dal.entity.Salle;

@Repository
public interface SalleRepository extends CrudRepository<Salle, Long> {

	List<Salle> findAll();

	Salle findById(Long id);

	Salle findByLibelle(String libelle);

	Page<Salle> findAll(Pageable pageable);

	Page<Salle> findByLibelleContainingIgnoreCase(String libelle, Pageable pageable);

}
