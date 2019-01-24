package br.com.respawntimer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class WelcomeController {

	@RequestMapping(method = RequestMethod.GET)
	public String getHomePage(Model model) {
		model.addAttribute("link", "sobre");

		return "sobre";
	}
}