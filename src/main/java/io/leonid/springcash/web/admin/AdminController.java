package io.leonid.springcash.web.admin;

import io.leonid.springcash.model.Role;
import io.leonid.springcash.model.User;
import io.leonid.springcash.service.IRoleService;
import io.leonid.springcash.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by leonid on 15.10.14.
 */
@Controller
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @Autowired
    private MessageSource messageSource;

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

        final String successMessage = messageSource.getMessage("valid.userseditsavesuccess", null, locale);
        redirectAttributes.addFlashAttribute("successMsg", successMessage);

        return "redirect:users.htm";
    }

    @RequestMapping(value = "/admin.htm", method = RequestMethod.GET)
    public String adminPage(final ModelMap map) {
        logger.info("Admin page controller");

        return "admin/admin";
    }
}
