package io.leonid.springcash.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
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

    private static final String LOGIN_PAGE = "/login.htm";
    private static final String LOGIN_VIEW = "security/login";
    private static final String ERROR403_PAGE = "/error403.htm";
    private static final String ERROR403_VIEW = "security/error403";

    @RequestMapping(value = LOGIN_PAGE, method = RequestMethod.GET)
    public String loginPage(final ModelMap map,
                            @RequestParam(value = "error", required = false) String error,
                            HttpServletRequest request) {
        if (error != null) {
            String errorMsg = getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION");
            map.addAttribute("error", errorMsg);
            logger.error("Error {}", errorMsg);

            //login form for update page
            //if login error, get the targetUrl from session again.
            String targetUrl = getRememberMeTargetUrlFromSession(request);
            logger.info("Target URL: {}", targetUrl);
            if(StringUtils.hasText(targetUrl)){
                map.addAttribute("targetUrl", targetUrl);
                map.addAttribute("loginUpdate", true);
            }
        }

        return LOGIN_VIEW;
    }

    @RequestMapping(value = ERROR403_PAGE, method = RequestMethod.GET)
    public String accesssDenied(final ModelMap map) {
        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            map.addAttribute("username", userDetail.getUsername());
        }

        logger.error("Access denied for user {}", map.get("username"));

        return ERROR403_VIEW;
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

    /**
     * customize the error message
     */
    private String getErrorMessage(HttpServletRequest request, String key) {
        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception != null) {
            error = exception.getMessage();
        }

        return error;
    }
}
