package fr.epsi.ipeda.controller;

import javax.transaction.Transactional;

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
	@Transactional
	public String generation(Model model) {

		databaseService.initialiserSpecificite();
		databaseService.initialiserAnneeScolaire();
		// databaseService.initialiserFormation();
		// databaseService.initialiserParcours();
		// databaseService.initialiserBlocDeCompetence();
		// databaseService.initialiserUniteEnseignement();

		// initialisation des salles
		databaseService.initialiserSalles();

		// initialisation des intervenants
		databaseService.initialiserIntervenants();

		// initialisation des types de périodes
		databaseService.initialiserPeriodeType();

		// databaseService.afficheCours();

		b1.initialiserFormation();
		b1.initialiserPlanif();
		// b2.initialiserFormation();
		// b3.initialiserFormation();
		// b3.initialiserCours();
		// i4.initialiserFormation();
		// i5.initialiserFormation();

		// databaseService.getPlanningBySemaine(Formation.TypeFormation.B3, new Semaine(2018, 8));

		return "test/dbinit";
	}

}
