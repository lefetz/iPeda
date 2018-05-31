package fr.epsi.ipeda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.epsi.ipeda.dal.entity.Formation;

@Controller
public class FormationController {

	@RequestMapping("/formation/read.html")
	public String readFormation(Model model) {
		model.addAttribute("cssActive_formations", "active");
		model.addAttribute("formation", new Formation());
		return "pages/formation/read";
	}

}
