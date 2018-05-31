package fr.epsi.ipeda.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.epsi.ipeda.dal.dto.CreneauDTO;
import fr.epsi.ipeda.dal.entity.AnneeScolaire;
import fr.epsi.ipeda.dal.entity.Formation;
import fr.epsi.ipeda.helpers.TimeUtils;
import fr.epsi.ipeda.helpers.TimeUtils.TIMEFIELD;
import fr.epsi.ipeda.model.service.anneeScolaire.AnneeScolaireService;
import fr.epsi.ipeda.model.service.formation.FormationService;

@Controller
public class PlanifController {

	@Autowired
	private AnneeScolaireService anneeScolaireService;

	@Autowired
	private FormationService formationService;

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

		CreneauDTO creneau = null;
		List<CreneauDTO> listCreneaux = new ArrayList<CreneauDTO>();

		// dates de la formation, ex B1
		AnneeScolaire anneeScolaire = anneeScolaireService.findByDateDebut(LocalDate.of(2018, 9, 1));
		Formation formation = formationService.findByLibelleContainingAndAnneeScolaire("BACHELOR 1", anneeScolaire);

		LocalDate dateDebut = formation.getAnneeScolaire().getDateDebut();
		LocalDate dateFin = formation.getAnneeScolaire().getDateFin();
		long daysBetween = ChronoUnit.DAYS.between(dateDebut, dateFin);

		LocalDate currentLocalDate = dateDebut;

		// pour chaque jour de la formation
		for (long i = 0; i < daysBetween; i++) {

			// matin
			creneau = new CreneauDTO();
			creneau.setGroupe(formation.getLibelleCourt());
			creneau.setDate(currentLocalDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			creneau.setHeureDebut("08h15");
			creneau.setHeureFin("12h15");
			creneau.setWeekEnd(currentLocalDate.getDayOfWeek() == DayOfWeek.SATURDAY || currentLocalDate.getDayOfWeek() == DayOfWeek.SUNDAY);
			listCreneaux.add(creneau);

			// après-midi
			creneau = new CreneauDTO();
			creneau.setGroupe(formation.getLibelleCourt());
			creneau.setDate(currentLocalDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			creneau.setHeureDebut("13h45");
			creneau.setHeureFin("17h45");
			creneau.setWeekEnd(currentLocalDate.getDayOfWeek() == DayOfWeek.SATURDAY || currentLocalDate.getDayOfWeek() == DayOfWeek.SUNDAY);
			listCreneaux.add(creneau);

			// maj date +1
			currentLocalDate = currentLocalDate.plusDays(1);
		}

		model.addAttribute("creneaux", listCreneaux);

		return "pages/planif/read";
	}

}
