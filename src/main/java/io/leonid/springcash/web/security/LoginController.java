package io.leonid.springcash.web.security;

import io.leonid.springcash.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by leonid on 26.09.2014.
 */
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login.htm", method = RequestMethod.GET)
    public String loginPage(final ModelMap map,
                            @RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            HttpServletRequest request) {
        logger.info("Login page controller");

        if (error != null) {
            //map.addAttribute("error", "Invalid username and password!");
            map.addAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
            logger.error("Error {}", error);
        }

        if (logout != null) {
            map.addAttribute("msg", "You've been logged out successfully.");

            return "home";
        }

        return "login";
    }

    // customize the error message
    private String getErrorMessage(HttpServletRequest request, String key) {
        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        error = exception.getMessage();
        /*if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else if (exception instanceof DisabledException) {
            error = exception.getMessage();
        }
        else {
            error = "Invalid username and password!";
        }*/

        return error;
    }

    /*@RequestMapping(value = "/performLogin.htm", method = RequestMethod.POST)
    public String handleLogin(final ModelMap map) {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        logger.info("Principal: {}", principal);

        return "home";
    }*/

    @RequestMapping(value = "/admin.htm", method = RequestMethod.GET)
    public String adminPage(final ModelMap map) {
        logger.info("Admin page controller");

        return "admin/admin";
    }

    @RequestMapping(value = "/error403.htm", method = RequestMethod.GET)
    public String accesssDenied(final ModelMap map) {
        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            map.addAttribute("username", userDetail.getUsername());
        }

        logger.error("Access denied for user {}", map.get("username"));

        return "error403";
    }
}
