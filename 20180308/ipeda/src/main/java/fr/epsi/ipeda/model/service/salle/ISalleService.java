package fr.epsi.ipeda.model.service.salle;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import fr.epsi.ipeda.dal.entity.Salle;

public interface ISalleService {

	List<Salle> getAllSalles();

	Page<Salle> getAllSalles(PageRequest pageRequest);

	Page<Salle> getSallesByLibelle(String libelle, PageRequest pageRequest);

	Salle save(Salle salle);

}
