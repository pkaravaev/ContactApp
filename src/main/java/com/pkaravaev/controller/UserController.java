package com.pkaravaev.controller;


import com.pkaravaev.command.LoginCommand;
import com.pkaravaev.command.UserCommand;
import com.pkaravaev.domain.User;
import com.pkaravaev.exception.UserBlockedException;
import com.pkaravaev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

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

    @RequestMapping(value = "/reg_form")
    public String registrationForm(Model model) {
        UserCommand cmd = new UserCommand();

        model.addAttribute("command", cmd);
        return "reg_form";
    }

    @RequestMapping(value = "/register")
    public String registerUser(@ModelAttribute("command") UserCommand cmd, Model model) {
        User user = cmd.getUser();
        user.setRole(UserService.ROLE_USER);
        user.setLoginStatus(UserService.LOGIN_STATUS_ACTIVE);
        userService.register(user);
        model.addAttribute("command", cmd);
        return "redirect:index?act=reg";
    }


    private void addUserInSession(User user, HttpSession session) {
        session.setAttribute("user", user);
        session.setAttribute("userid", user.getUserid());
        session.setAttribute("role", user.getRole());
    }

}
