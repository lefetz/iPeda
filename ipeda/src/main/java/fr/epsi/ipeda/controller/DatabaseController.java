package fr.epsi.ipeda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.epsi.ipeda.dal.service.IDatabaseService;
import fr.epsi.ipeda.dal.service.feeding.an1718.B1;

@Controller
public class DatabaseController {

	@Autowired
	private IDatabaseService databaseService;

	@Autowired
	private B1 b1;

	// @Autowired
	// private B2 b2;
	//
	// @Autowired
	// private B3 b3;
	//
	// @Autowired
	// private I4 i4;
	//
	// @Autowired
	// private I5 i5;

	@RequestMapping("/db/init")
	public String generation(Model model) {

		databaseService.initialiserSpecificite();
		databaseService.initialiserAnneeScolaire();

		// initialisation des salles
		databaseService.initialiserSalles();

		// initialisation des intervenants
		databaseService.initialiserIntervenants();

		// initialisation des types de périodes
		databaseService.initialiserPeriodeType();

		// force commit

		b1.initialiserFormation();
		b1.initialiserPeriodes();
		b1.initialiserSeances();

		return "test/dbinit";
	}

}
