package fr.epsi.ipeda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.epsi.ipeda.dal.entity.AnneeScolaire;
import fr.epsi.ipeda.dal.entity.Formation;
import fr.epsi.ipeda.model.service.anneeScolaire.IAnneeScolaireService;

@Controller
public class FormationController {

	@Autowired
	private IAnneeScolaireService anneeScolaireService;

	@RequestMapping("/formation/read.html")
	public String readFormation(Model model) {
		model.addAttribute("cssActive_formations", "active");

		model.addAttribute("formation", new Formation());

		List<AnneeScolaire> listAnneeScolaire = anneeScolaireService.findAll();

		model.addAttribute("listAnneeScolaire", listAnneeScolaire);
		return "pages/formation/read";
	}

}
