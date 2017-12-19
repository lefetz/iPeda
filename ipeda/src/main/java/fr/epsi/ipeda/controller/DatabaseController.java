package fr.epsi.ipeda.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.epsi.ipeda.service.database.IDatabaseService;

@Controller
public class DatabaseController {

	@Autowired
	private IDatabaseService databaseService;

	@RequestMapping("/db/init")
	@Transactional
	public String generation(Model model) {

		// initialisation des salles
		databaseService.initialiserSalles();

		// initialisation des intervenants
		databaseService.initialiserIntervenants();

		// initialisation des modules
//		databaseService.initialiserModules();

		// initialiser UE
//		databaseService.initialiserUE();

		// initialiser blocs de compétences
//		databaseService.initialiserBlocsCompetences();

		// initialiser parcours
//		databaseService.initialiserParcours();

		// initialisation des formations
		databaseService.initialiserFormations();

		return "test/dbinit";
	}

}
