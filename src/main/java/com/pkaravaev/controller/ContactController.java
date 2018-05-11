package com.pkaravaev.controller;


import com.pkaravaev.domain.Contact;
import com.pkaravaev.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String saveOrUpdate(@ModelAttribute("Command") Contact contact, HttpSession session, Model model) {

        Integer contactId = (Integer) session.getAttribute("aContactId");
        if (contactId == null) {
            //save
            try {
                Integer userId = (Integer) session.getAttribute("userid");
                contact.setUserid(userId);
                service.save(contact);
                return "redirect:clist?act=sv";
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("err", "Failed to save contact");
                return "contact_form";
            }

        } else {
            //update
            try {
                contact.setContactid(contactId);
                service.update(contact);
                return "redirect:clist?act=ed";
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("err", "Failed to Edit contact");
                return "contact_form";
            }
        }

    }

    @RequestMapping(value = "/user/clist")
    public String contactList(Model model, HttpSession session) {
        List<Contact> list = service.findUserContact((Integer) session.getAttribute("userid"));
        model.addAttribute("contactList", list);
        return "clist";
    }

    @RequestMapping(value = "/user/contact_search")
    public String contactSearch(Model model, HttpSession session, @RequestParam("freeText") String freeText) {

        Integer userid = (Integer) session.getAttribute("userid");
        model.addAttribute("contactList", service.findUserContact(userid, freeText));
        return "clist";
    }

    @RequestMapping(value = "/user/del_contact")
    public String deleteContact(@RequestParam("cid") Integer contactId) {
        service.delete(contactId);
        return "redirect:clist?act=del";
    }

    @RequestMapping(value = "/user/edit_contact")
    public String prepareEditForm(@RequestParam("cid") Integer contactId, Model model, HttpSession session) {
        session.setAttribute("aContactId", contactId);
        Contact c = service.findById(contactId);
        model.addAttribute("command", c);
        return "contact_form";
    }
}
