package io.leonid.springcash.web.security;

import io.leonid.springcash.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by leonid on 26.09.2014.
 */
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login.htm", method = RequestMethod.GET)
    public String redirectToLoginPage(final ModelMap map) {
        logger.info("Login page controller");
        return "login";
    }

    @RequestMapping(value = "/performLogin.htm", method = RequestMethod.POST)
    public String handleLogin(final ModelMap map) {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        logger.info("Principal: {}", principal);

        return "home";
    }
}
