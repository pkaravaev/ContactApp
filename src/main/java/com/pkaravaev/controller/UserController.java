package com.pkaravaev.controller;


import com.pkaravaev.command.LoginCommand;
import com.pkaravaev.command.UserCommand;
import com.pkaravaev.domain.User;
import com.pkaravaev.exception.UserBlockedException;
import com.pkaravaev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/index", "/"})
    public String index(Model m) {
        m.addAttribute("command", new LoginCommand());
        return "index";
    }

    @RequestMapping(value = {"/logout"})
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:index?act=lo";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleLogin(@ModelAttribute("command") LoginCommand cmd, Model model, HttpSession session) {
        try {
            User loggedUser = userService.login(cmd.getLoginName(), cmd.getPassword());
            if (loggedUser == null) {
                model.addAttribute("err", "Login Failed Enter valid credentials.");
                return "index";
            } else {
                if (loggedUser.getRole().equals(UserService.ROLE_USER)) {
                    addUserInSession(loggedUser, session);
                    model.addAttribute("user", loggedUser);

                    return "redirect:user/dashboard";
                } else if (loggedUser.getRole().equals(UserService.ROLE_ADMIN)) {
                    addUserInSession(loggedUser, session);
                    model.addAttribute("user", loggedUser);

                    return "redirect:admin/dashboard";
                } else {
                    model.addAttribute("err", "Invalid User ROLE");
                    return "index";
                }
            }
        } catch (UserBlockedException e) {
            model.addAttribute("err", e.getMessage());
            return "index";
        }
    }

    @RequestMapping(value = {"/user/dashboard"})
    public String userDashboard() {
        return "dashboard_user";
    }

    @RequestMapping(value = {"/admin/dashboard"})
    public String adminDashboard() {
        return "dashboard_admin";
    }

    @RequestMapping(value = {"/admin/users"})
    public String getUserList(Model model) {

        List<User> userList = userService.getUserList();
        model.addAttribute("users", userService.getUserList());
        return "users";
    }

    @RequestMapping(value = "/reg_form")
    public String registrationForm(Model model) {
        UserCommand cmd = new UserCommand();
        model.addAttribute("command", cmd);
        return "reg_form";
    }

    @RequestMapping(value = "/register")
    public String registerUser(@ModelAttribute("command") UserCommand cmd, Model model) {
        try {
            User user = cmd.getUser();
            user.setRole(UserService.ROLE_USER);
            user.setLoginStatus(UserService.LOGIN_STATUS_ACTIVE);
            userService.register(user);
            model.addAttribute("command", cmd);
            return "redirect:index?act=reg";
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            model.addAttribute("err", "Username is already registered.Please select another username");
            return "reg_form";
        }
    }

    private void addUserInSession(User user, HttpSession session) {
        session.setAttribute("user", user);
        session.setAttribute("userid", user.getUserid());
        session.setAttribute("role", user.getRole());
    }

    @RequestMapping(value = "/admin/change_status")
    @ResponseBody
    public String changeLoginStatus(@RequestParam Integer userId, @RequestParam Integer loginStatus) {
        try {
            userService.changeLoginStatus(userId, loginStatus);
            return "SUCCESS: Status Changed";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: Unable to Change Status";
        }
    }

    @RequestMapping(value = "/check_avail")
    @ResponseBody
    public String checkAvailability(@RequestParam String username) {
        if (userService.isUserNameExist(username)) {
            return "This username is already taken";
        } else {
            return "Yes! You can take this";
        }
    }
}
