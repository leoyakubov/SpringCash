package io.leonid.springcash.web.user;

import io.leonid.springcash.model.Role;
import io.leonid.springcash.model.User;
import io.leonid.springcash.service.IRoleService;
import io.leonid.springcash.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by leonid on 14.08.14.
 */
@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @Autowired
    private MessageSource messageSource;

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

    @RequestMapping("/user/list.htm")
    public String listUsers(Map<String, Object> map) {
        logger.info("UserController.listUsers called");

        return "user/list";
    }

    @RequestMapping(value = "/user/add.htm", method = RequestMethod.GET)
    public String prepareAddUserForm(ModelMap modelMap) {
        UserModel userModel = new UserModel();
        modelMap.addAttribute("userModel", userModel);

        return "user/add";
    }

    @RequestMapping(value = "/user/add.htm", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("userModel")
                              UserModel userModel, BindingResult result) {
        userModel.setRole(roleService.findByName(userModel.getRole().getName()));

        userValidator.validate(userModel,result);
        if(result.hasErrors()) {
            return "user/add";
        }

        //Encode plain text password into bcrypt hash
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));

        User user = userModel.constructUserFromModel();
        userService.insertOrUpdate(user);

        return "redirect:/user/list.htm";
    }

    @RequestMapping(value = "/user/edit.htm", method = RequestMethod.GET)
    public String prepareEditUserForm(Locale locale, HttpServletRequest request, ModelMap modelMap,
                                      final RedirectAttributes redirectAttributes) {
        String userId = request.getParameter("userId");
        User user = userService.findByID(Integer.parseInt(userId));

        if (user == null) {
            final String errorMessage = messageSource.getMessage("valid.nosuchuser", null, locale);;
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

            return "redirect:/user/list.htm";
        }

        UserModel userModel = new UserModel(user);
        modelMap.addAttribute("userModel", userModel);

        return "user/edit";
    }

    @RequestMapping(value = "/user/edit.htm", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("userModel") UserModel userModel,
                           BindingResult result, Locale locale,
                           final RedirectAttributes redirectAttributes) {
        userModel.setRole(roleService.findByName(userModel.getRole().getName()));

        userValidator.validate(userModel, result);
        if(result.hasErrors()) {
            return "user/edit";
        }

        User user = userModel.constructUserFromModel();
        userService.insertOrUpdate(user);
        final String successMessage = messageSource.getMessage("valid.usersavesuccess", null, locale);;
        redirectAttributes.addFlashAttribute("successMessage", successMessage);

        return "redirect:/user/list.htm";
    }

    @RequestMapping("/user/delete.htm")
    public String deleteUser(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        User user = userService.findByID(Integer.parseInt(userId));
        userService.delete(user);

        return "redirect:/user/list.htm";
    }
}
