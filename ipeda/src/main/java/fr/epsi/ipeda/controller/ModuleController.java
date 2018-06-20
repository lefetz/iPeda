package fr.epsi.ipeda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.epsi.ipeda.dal.entity.Module;

@Controller
public class ModuleController {

	@RequestMapping("/module/read.html")
	public String readModule(Model model) {
		model.addAttribute("cssActive_modules", "active");
		model.addAttribute("module", new Module());
		return "pages/module/read";
	}

}
