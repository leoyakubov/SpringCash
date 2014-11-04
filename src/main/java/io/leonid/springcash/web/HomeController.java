package io.leonid.springcash.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private static final String HOME_PAGE = "/home.htm";
    private static final String HOME_VIEW = "home";

    private static int callNumber = 1;

	@RequestMapping(value = HOME_PAGE, method = RequestMethod.GET)
	public String printWelcome(ModelMap model, @RequestParam(value = "logout", required = false) String logout) {
        logger.info("Welcome home! Current time is: " + new Date());
        logger.info("Call number: {}", callNumber++);

        if (logout != null) {
            model.addAttribute("successMsg", "You've been logged out successfully.");
        }

		return HOME_VIEW;
	}
}