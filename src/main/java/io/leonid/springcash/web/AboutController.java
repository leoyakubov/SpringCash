package io.leonid.springcash.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by leonid on 21.08.2014.
 */
@Controller
@RequestMapping("/about.htm")
public class AboutController {
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("aboutMsg", "About page");
        return "about";
    }
}
