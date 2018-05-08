package com.pkaravaev.controller;


import com.pkaravaev.domain.Contact;
import com.pkaravaev.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ContactController {
    @Autowired
    private ContactService service;

    @RequestMapping(value = "/user/contact_form")
    public String contactForm(Model m) {
        Contact contact = new Contact();
        m.addAttribute("command", contact);
        return "contact_form";
    }

    @RequestMapping(value = "/user/save_contact")
    public String saveContact(@ModelAttribute("Command") Contact contact, HttpSession session, Model model) {
        try {
            contact.setUserid((Integer) session.getAttribute("userid"));
            service.save(contact);
            return "redirect:clist?act=sv";
        } catch (Exception ex) {
            ex.printStackTrace();
            model.addAttribute("command", contact);
            model.addAttribute("err", "Failed to save contact");
            return "contact_form";
        }
    }

    @RequestMapping(value = "/user/clist")
    public String contactList(Model model, HttpSession session) {
        List<Contact> list = service.findUserContact((Integer) session.getAttribute("userid"));
        model.addAttribute("contactList", list);
        return "clist";
    }
}
