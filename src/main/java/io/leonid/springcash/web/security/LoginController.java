package io.leonid.springcash.web.security;

import io.leonid.springcash.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
            String errorMsg = getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION");
            map.addAttribute("error", errorMsg);
            logger.error("Error {}", errorMsg);

            //login form for update page
            //if login error, get the targetUrl from session again.
            String targetUrl = getRememberMeTargetUrlFromSession(request);
            System.out.println(targetUrl);
            if(StringUtils.hasText(targetUrl)){
                map.addAttribute("targetUrl", targetUrl);
                map.addAttribute("loginUpdate", true);
            }
        }

        if (logout != null) {
            map.addAttribute("msg", "You've been logged out successfully.");

            return "home";
        }

        return "login";
    }

    /**
     * This update page is for user login with password only.
     * If user is login via remember me cookie, send login to ask for password again.
     * To avoid stolen remember me cookie to update info
     */
    @RequestMapping(value = "/admin/update.htm", method = RequestMethod.GET)
    public String updatePage(final ModelMap map, HttpServletRequest request) {
        String view = "";

        if (isRememberMeAuthenticated()) {
            //send login for update
            setRememberMeTargetUrlToSession(request);
            map.addAttribute("loginUpdate", true);
            view = "/login";

        } else {
            view = "/admin/update";
        }

        return view;
    }

    /**
     * Check if user is login by remember me cookie, refer
     * org.springframework.security.authentication.AuthenticationTrustResolverImpl
     */
    private boolean isRememberMeAuthenticated() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }

        return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
    }

    /**
     * save targetURL in session
     */
    private void setRememberMeTargetUrlToSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.setAttribute("targetUrl", "/admin/update.htm");
        }
    }

    /**
     * get targetURL from session
     */
    private String getRememberMeTargetUrlFromSession(HttpServletRequest request){
        String targetUrl = "";
        HttpSession session = request.getSession(false);
        if(session!=null){
            targetUrl = session.getAttribute("targetUrl")==null?""
                    :session.getAttribute("targetUrl").toString();
        }
        return targetUrl;
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

    /*@ExceptionHandler(UsernameNotFoundException.class)
    public String handleMyAppTechnicalException(UsernameNotFoundException e, Model model) {

        // Compute your generic error message/code with e.
        // Or just use a generic error/code, in which case you can remove e from the parameters
        String genericErrorMessage = "Some technical exception has occured blah blah blah" ;

        // There are many other ways to pass an error to the view, but you get the idea
        model.addAttribute("myErrors", genericErrorMessage);

        return "myView";
    }*/
}
