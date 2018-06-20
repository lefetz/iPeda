package fr.epsi.ipeda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

	@RequestMapping("/dashboard")
	public String readDashboard(Model model) {
		model.addAttribute("cssActive_dashboard", "active");
		return "dashboard";
	}

}
