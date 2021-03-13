package com.company.controllers;

import com.company.builder.Email;
import com.company.dao.EmailDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailController {

    private final EmailDAO emailDAO;

    public EmailController(EmailDAO emailDAO) {
        this.emailDAO = emailDAO;
    }

    @GetMapping("/")
    public String showEmailPage(Model model) {
        model.addAttribute("newEmail", new Email()); // Trimitem un obiect de tip Email gol in view
        return "/email/index";
    }

    @GetMapping("/emailsSent")
    public String showsAllEmailsSent(Model model) {
        model.addAttribute("emailsSent", emailDAO.showListOfEmailSent());
        return "/email/allEmails";
    }

    @PostMapping("/sendEmail")
    public String sendEmail(@ModelAttribute("newEmail") Email email) {  // Primim obiectul din view plin
        emailDAO.save(email); // SAVE EMAIL
        emailDAO.sendEmail(email); // SEND EMAIL

        return "/email/index";
    }

}
