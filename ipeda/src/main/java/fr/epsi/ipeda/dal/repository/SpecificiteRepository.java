package fr.epsi.ipeda.dal.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.epsi.ipeda.dal.entity.Specificite;

@Repository
public interface SpecificiteRepository extends CrudRepository<Specificite, Long> {

	List<Specificite> findAll();

	Specificite findById(Long id);

	Specificite findByLibelle(String libelle);

	Page<Specificite> findAll(Pageable pageable);

	Page<Specificite> findByLibelleContainingIgnoreCase(String libelle, Pageable pageable);

}
