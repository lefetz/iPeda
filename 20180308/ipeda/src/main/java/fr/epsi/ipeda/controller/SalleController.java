package fr.epsi.ipeda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SalleController {

	@RequestMapping("/salle/read.html")
	public String readSalle(Model model) {
		model.addAttribute("cssActive_salles", "active");
		return "pages/salle/read";
	}

	@RequestMapping("/salle/update.html")
	public String updateSalle(Model model) {
		model.addAttribute("cssActive_salles", "active");
		return "pages/salle/update";
	}

	@RequestMapping("/salle/delete.html")
	public String deleteSalle(Model model) {
		model.addAttribute("cssActive_salles", "active");
		return "pages/salle/read";
	}

}
