package fr.epsi.ipeda.model.service.formation;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import fr.epsi.ipeda.dal.entity.Formation;

public interface IFormationService {

	List<Formation> findAll();

	Page<Formation> findAll(PageRequest pageRequest);

	Formation save(Formation object);

	void delete(Formation object);

}
