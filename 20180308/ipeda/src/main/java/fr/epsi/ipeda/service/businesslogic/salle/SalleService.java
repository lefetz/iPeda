package fr.epsi.ipeda.service.businesslogic.salle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.epsi.ipeda.dao.entity.Salle;
import fr.epsi.ipeda.dao.repository.SalleRepository;

@Service
public class SalleService implements ISalleService {
	
	@Autowired
	private SalleRepository salleRepository;

	@Override
	public List<Salle> getAllSalles() {
		return salleRepository.findAll();
	}
	
	

}
