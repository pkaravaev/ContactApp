package com.pkaravaev.controller;


import com.pkaravaev.command.LoginCommand;
import com.pkaravaev.domain.User;
import com.pkaravaev.exception.UserBlockedException;
import com.pkaravaev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/index", "/"})
    public String index(Model m) {
        m.addAttribute("command", new LoginCommand());
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleLogin(@ModelAttribute("command") LoginCommand cmd, Model model) {
        try {
            User loggedUser = userService.login(cmd.getLoginName(), cmd.getPassword());

            if (loggedUser == null) {
                model.addAttribute("err", "Login Failed Enter valid credentials.");
                return "index";
            } else {
                if (loggedUser.getRole().equals(UserService.ROLE_USER)) {
                    model.addAttribute("user", loggedUser);
                    return "redirect:user/dashboard";
                }
                else if(loggedUser.getRole().equals(UserService.ROLE_ADMIN)){
                    model.addAttribute("user", loggedUser);
                    return "redirect:admin/dashboard";
                }
                else {
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

}
