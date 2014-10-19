package io.leonid.springcash.web.user;

import io.leonid.springcash.model.Role;
import io.leonid.springcash.model.User;
import io.leonid.springcash.service.IRoleService;
import io.leonid.springcash.service.IUserService;
import io.leonid.springcash.web.generic.GenericController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

/**
 * Created by leonid on 14.08.14.
 */
@Controller
public class UserController extends GenericController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @Autowired
    @Qualifier("userValidator")
    private UserValidator userValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @ModelAttribute("roleList")
    public List<Role> roleList() {
        return roleService.findAll();
    }

    @ModelAttribute("userList")
    public List<User> userList() {
        return userService.findAll();
    }


    /**
     * This edit page is for user login with password only.
     * If user is login via remember me cookie, send login to ask for password again.
     * To avoid stolen remember me cookie to update info
     */
    @RequestMapping(value = "/user/edit.htm", method = RequestMethod.GET)
    public String prepareEditUserForm(Locale locale, HttpServletRequest request, ModelMap modelMap,
                                      final RedirectAttributes redirectAttributes) {
        String view = "";

        if (isRememberMeAuthenticated()) {
            //send login for update
            setRememberMeTargetUrlToSession(request);
            modelMap.addAttribute("loginUpdate", true);
            view = "security/login";

        } else {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userLogin = auth.getName(); //get logged in username
            User user = userService.findByLogin(userLogin);

            if (user == null) {
                putRedirectMessage(ERROR_MSG, "msg.nosuchuser", locale, redirectAttributes);

                return "redirect:/home.htm";
            }

            UserModel userModel = new UserModel(user);
            modelMap.addAttribute("userModel", userModel);

            view = "user/edit";
        }

        return view;
    }

    @RequestMapping(value = "/user/edit.htm", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("userModel") UserModel userModel,
                           BindingResult result, Locale locale,
                           final RedirectAttributes redirectAttributes) {
        userModel.setRole(roleService.findByID(userModel.getRole().getId()));

        userValidator.validate(userModel, result);
        if(result.hasErrors()) {
            return "user/edit";
        }

        User user = userModel.constructUserFromModel();
        userService.insertOrUpdate(user);
        putRedirectMessage(SUCCESS_MSG, "msg.user.save.success", locale, redirectAttributes);

        return "redirect:/user/edit.htm";
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
            session.setAttribute("targetUrl", "/user/edit.htm");
        }
    }
}
