package fr.epsi.ipeda.controller;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.epsi.ipeda.helpers.TimeUtils;
import fr.epsi.ipeda.helpers.TimeUtils.TIMEFIELD;

@Controller
public class PlanifController {

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
		



		return "pages/planif/read";
	}

}
