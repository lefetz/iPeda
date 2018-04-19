package fr.epsi.ipeda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.epsi.ipeda.dao.entity.Salle;
import fr.epsi.ipeda.service.businesslogic.salle.SalleService;

@RestController
public class RestRequestController {

	@Autowired
	private SalleService salleService;

	@GetMapping("/rest/salles")
	public List<Salle> getAllSalles(Model model) {
		return salleService.getAllSalles();
	}

}
