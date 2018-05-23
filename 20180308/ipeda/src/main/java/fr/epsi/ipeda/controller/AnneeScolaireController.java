package fr.epsi.ipeda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.epsi.ipeda.dal.entity.AnneeScolaire;

@Controller
public class AnneeScolaireController {

	@RequestMapping("/anneeScolaire/read.html")
	public String readAnneeScolaire(Model model) {
		model.addAttribute("cssActive_anneesScolaires", "active");
		model.addAttribute("anneeScolaire", new AnneeScolaire());
		return "pages/anneeScolaire/read";
	}

}
