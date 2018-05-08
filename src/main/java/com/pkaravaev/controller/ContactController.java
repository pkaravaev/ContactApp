package com.pkaravaev.controller;


import com.pkaravaev.domain.Contact;
import com.pkaravaev.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ContactController {

    private ContactService service;

    @RequestMapping(value = "/user/contact_form")
    public String contactForm(Model m) {
        Contact contact = new Contact();
        m.addAttribute("command", contact);
        return "contact_form";
    }

    @RequestMapping(value = "/user/save_contact")
    public String saveContact(@ModelAttribute("Command") Contact contact, HttpSession session) {
        contact.setUserid((Integer)session.getAttribute("userid"));
        service.save(contact);
        return "contact_form";
    }
}
