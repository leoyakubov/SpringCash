package io.leonid.springcash.web.security;

import io.leonid.springcash.model.Role;
import io.leonid.springcash.model.User;
import io.leonid.springcash.service.IRoleService;
import io.leonid.springcash.service.IUserService;
import io.leonid.springcash.web.generic.GenericController;
import io.leonid.springcash.web.user.UserModel;
import io.leonid.springcash.web.user.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

/**
 * Created by leonid on 18.10.14.
 */
@Controller
public class SignupController extends GenericController{
    private static final Logger logger = LoggerFactory.getLogger(SignupController.class);

    private static final String SIGNUP_PAGE = "/signup.htm";
    private static final String SIGNUP_VIEW = "security/signup";
    private static final String HOME_PAGE = "/home.htm";

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

    @RequestMapping(value = SIGNUP_PAGE, method = RequestMethod.GET)
    public String prepareSignupForm(ModelMap modelMap) {
        UserModel userModel = new UserModel();
        modelMap.addAttribute("userModel", userModel);

        return SIGNUP_VIEW;
    }

    @RequestMapping(value = SIGNUP_PAGE, method = RequestMethod.POST)
    public String signUp(@ModelAttribute("userModel")
                         UserModel userModel, BindingResult result,
                         Locale locale, final RedirectAttributes redirectAttributes) {
        //Set default role for a new user
        Role userRole = roleService.findByName(Role.ROLE_USER);
        userModel.setRole(userRole);

        userValidator.validate(userModel, result);
        if(result.hasErrors()) {
            return SIGNUP_VIEW;
        }

        //Encode plain text password into bcrypt hash
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));

        User user = userModel.constructUserFromModel();
        userService.insert(user);
        putRedirectMessage(SUCCESS_MSG, "msg.signup.success", locale, redirectAttributes);

        return "redirect:" + HOME_PAGE;
    }
}
