package org.s2n.ddt.ddtTests.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class BaseController {

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(ModelMap model) {

		model.addAttribute("message",
				"Ddt Tests ");

		return "index";

	}

	@RequestMapping(value = "/welcome/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, ModelMap model) {

		model.addAttribute("message", "Ddt Tests , user: "
				+ name);
		return "index";

	}
	///p/ddt/2/w/DdtTests/src/main/java/org/s2n/ddt/ddtTests/web/controller/BaseController.java

}

