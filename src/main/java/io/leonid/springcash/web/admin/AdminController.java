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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by leonid on 15.10.14.
 */
@Controller
public class AdminController extends GenericController{
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private static final String EDIT_USERS_PAGE = "/admin/editUsers.htm";
    private static final String EDIT_USERS_VIEW = "admin/editUsers";
    private static final String ADD_USER_PAGE = "/admin/addUser.htm";
    private static final String ADD_USER_VIEW = "admin/addUser";
    private static final String SAVE_USERS_PAGE = "/admin/saveUsers.htm";
    private static final String DELETE_USER_PAGE = "/admin/deleteUser.htm";
    private static final String CONSOLE_PAGE = "/admin/console.htm";
    private static final String CONSOLE_VIEW = "admin/console";

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

    @ModelAttribute("userListModel")
    public UserListModel users() {
        UserListModel userListModel = new UserListModel(userList());

        return userListModel;
    }

    @RequestMapping(value = ADD_USER_PAGE, method = RequestMethod.GET)
    public String prepareAddUserForm(ModelMap modelMap) {
        UserModel userModel = new UserModel();
        modelMap.addAttribute("userModel", userModel);

        return ADD_USER_VIEW;
    }

    @RequestMapping(value = ADD_USER_PAGE, method = RequestMethod.POST)
    public String addUser(@ModelAttribute("userModel") UserModel userModel, BindingResult result,
                          Locale locale, final RedirectAttributes redirectAttributes) {
        userModel.setRole(roleService.findByName(userModel.getRole().getName()));

        userValidator.validate(userModel,result);
        if(result.hasErrors()) {
            return ADD_USER_VIEW;
        }

        //Encode plain text password into bcrypt hash
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));

        User user = userModel.constructUserFromModel();
        userService.insertOrUpdate(user);
        putRedirectMessage(SUCCESS_MSG, "msg.user.add.success", locale, redirectAttributes);

        return "redirect:" + EDIT_USERS_PAGE;
    }

    @RequestMapping(value = EDIT_USERS_PAGE, method = RequestMethod.GET)
    public String listUsers(Map<String, Object> map) {
        return EDIT_USERS_VIEW;
    }

    @RequestMapping(value = SAVE_USERS_PAGE, method = RequestMethod.POST)
    public String saveUsers(@ModelAttribute("userListModel") UserListModel userListModel, BindingResult result,
                            Locale locale, final RedirectAttributes redirectAttributes) {

        if (userListModel != null && userListModel.getUserListItems().size() > 0) {
            List<User> users = getChangedUsers(userListModel);
            if (users != null && users.size() > 0) {
                userService.insertOrUpdateMultipleEntities(users);
                putRedirectMessage(SUCCESS_MSG, "msg.users.edit.success", locale, redirectAttributes);
            }
        }

        return "redirect:" + EDIT_USERS_PAGE;
    }

    @RequestMapping(DELETE_USER_PAGE)
    public String deleteUser(HttpServletRequest request, Locale locale, final RedirectAttributes redirectAttributes) {
        String userId = request.getParameter("userId");
        User user = userService.findByID(Integer.parseInt(userId));
        if (user != null) {
            userService.delete(user);
            putRedirectMessage(SUCCESS_MSG, "msg.user.delete.success", locale, redirectAttributes);
        }
        else {
            putRedirectMessage(ERROR_MSG, "msg.user.delete.failure", locale, redirectAttributes);
        }

        return "redirect:" + EDIT_USERS_PAGE;
    }

    @RequestMapping(value = CONSOLE_PAGE, method = RequestMethod.GET)
    public String adminPage(final ModelMap map) {
        return CONSOLE_VIEW;
    }

    private List<User> getChangedUsers(UserListModel userListModel) {
        List<User> users = new ArrayList<>();

        List<UserListItem> items = userListModel.getUserListItems();
        for (UserListItem item : items) {
            if (item.isUpdated()) {
                User user = constructUserFromUserItem(item);
                users.add(user);
            }
        }

        return users;
    }

    private User constructUserFromUserItem(UserListItem item) {
        User user = new User();
        user.setId(item.getId());
        user.setLogin(item.getLogin());
        user.setPassword(item.getPassword());
        user.setEmail(item.getEmail());
        user.setActive(item.isActive());
        user.setRole(roleService.findByName(item.getRole().getName()));
        user.setFirstName(item.getFirstName());
        user.setLastName(item.getLastName());

        return user;
    }
}
