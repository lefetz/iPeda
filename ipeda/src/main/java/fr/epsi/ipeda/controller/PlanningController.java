package fr.epsi.ipeda.controller;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.epsi.ipeda.dal.entity.AnneeScolaire;
import fr.epsi.ipeda.dal.entity.Formation;
import fr.epsi.ipeda.helpers.TimeUtils;
import fr.epsi.ipeda.helpers.TimeUtils.TIMEFIELD;
import fr.epsi.ipeda.model.service.anneeScolaire.AnneeScolaireService;
import fr.epsi.ipeda.model.service.formation.FormationService;
import fr.epsi.ipeda.model.service.planning.PlanningService;

@Controller
public class PlanningController {

	@Autowired
	private PlanningService planningService;

	@Autowired
	private FormationService formationService;

	@Autowired
	private AnneeScolaireService anneeScolaireService;

	@RequestMapping("/planif/test")
	public String edt(Model model) {

		LocalDate aujourdhui = LocalDate.now();

		// semaine actuelle
		model.addAttribute("semaineActuelle", TimeUtils.getNextWeek(aujourdhui, 0));

		// récupération de la liste des semaines, de la semaine actuelle à semaine +3
		List<Map<TIMEFIELD, Integer>> listeSemainesSuivantes = new LinkedList<Map<TIMEFIELD, Integer>>();
		for (int i = 1; i <= 3; i++) {
			listeSemainesSuivantes.add(TimeUtils.getNextWeek(aujourdhui, i));
		}
		model.addAttribute("listeSemainesSuivantes", listeSemainesSuivantes);

		return "pages/landing";
	}

	@RequestMapping("/planif")
	public String planif(Model model) {
		AnneeScolaire anneeScolaire = anneeScolaireService.findByDateDebut(LocalDate.of(2018, 9, 1));
		Formation formation = formationService.findByLibelleContainingAndAnneeScolaire("BACHELOR 1", anneeScolaire);
		model.addAttribute("listeSeances", planningService.getListeSeances(formation));
		return "pages/planif/read";
	}

}
