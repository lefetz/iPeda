package fr.epsi.ipeda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/salles")
	public String genererSalles(Model model) {
		model.addAttribute("cssActive_salles", "active");
		return "pages/salles";
	}

	@RequestMapping("/dashboard")
	public String genererDashboard(Model model) {
		model.addAttribute("cssActive_dashboard", "active");
		return "dashboard";
	}

}
