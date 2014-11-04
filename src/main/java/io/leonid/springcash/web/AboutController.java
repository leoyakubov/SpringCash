package io.leonid.springcash.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by leonid on 21.08.2014.
 */
@Controller
public class AboutController {

    private static final String ABOUT_PAGE = "/about.htm";
    private static final String ABOUT_VIEW = "about";

    @RequestMapping(value = ABOUT_PAGE, method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("aboutMsg", "About page");
        return ABOUT_VIEW;
    }
}
