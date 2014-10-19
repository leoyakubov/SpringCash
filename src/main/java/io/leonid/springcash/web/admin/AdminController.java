package io.leonid.springcash.web.admin;

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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by leonid on 15.10.14.
 */
@Controller
public class AdminController extends GenericController{
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @Autowired
    @Qualifier("userValidator")
    private UserValidator userValidator;

    @InitBinder("userModel")
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

    @ModelAttribute("userListCommand")
    public UserListModel users() {
        UserListModel userListModel = new UserListModel(userList());

        return userListModel;
    }

    @RequestMapping(value = "/addUser.htm", method = RequestMethod.GET)
    public String prepareAddUserForm(ModelMap modelMap) {
        logger.info("AdminController.addUser called");

        UserModel userModel = new UserModel();
        modelMap.addAttribute("userModel", userModel);

        return "admin/addUser";
    }

    @RequestMapping(value = "/addUser.htm", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("userModel")
                          UserModel userModel, BindingResult result) {
        userModel.setRole(roleService.findByName(userModel.getRole().getName()));

        userValidator.validate(userModel,result);
        if(result.hasErrors()) {
            return "admin/addUser";
        }

        //Encode plain text password into bcrypt hash
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));

        User user = userModel.constructUserFromModel();
        userService.insertOrUpdate(user);

        return "redirect:/users.htm";
    }

    @RequestMapping(value = "/users.htm", method = RequestMethod.GET)
    public String listUsers(Map<String, Object> map) {
        logger.info("UserController.listUsers called");

        return "admin/users";
    }

    @RequestMapping(value = "/saveUsers.htm", method = RequestMethod.POST)
    public String saveUsers(@ModelAttribute("users") UserListModel userListModel, BindingResult result,
                            Locale locale, final RedirectAttributes redirectAttributes) {
        if (userListModel != null && userListModel.getUsers().size() > 0) {
            /*List<User> users = userListModel.getChangedUsers();
            for (User user : users) {
                userService.insertOrUpdate(user);
            }*/
        }

        putRedirectMessage(SUCCESS_MSG, "msg.users.edit.save.success", locale, redirectAttributes);

        return "redirect:users.htm";
    }

    @RequestMapping("/deleteUser.htm")
    public String deleteUser(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        User user = userService.findByID(Integer.parseInt(userId));
        userService.delete(user);

        return "redirect:/users.htm";
    }

    @RequestMapping(value = "/admin.htm", method = RequestMethod.GET)
    public String adminPage(final ModelMap map) {
        logger.info("Admin page controller");

        return "admin/admin";
    }
}
