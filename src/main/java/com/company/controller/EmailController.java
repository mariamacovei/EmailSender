package com.company.controller;

import com.company.model.Email;
import com.company.repository.EmailRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/emails")
public class EmailController {

    private final EmailRepository emailRepository;

    public EmailController(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;

    }

    @GetMapping()
    public String showEmailPage(Model model) {
        model.addAttribute("newEmail", new Email()); // Trimitem un obiect de tip Email gol in view
        return "/email/index";
    }

    @GetMapping("/list")
    public String showsAllEmailsSent(Model model) {
        model.addAttribute("listOfemails", emailRepository.readInbox());
        return "/email/allEmails";
    }

    @PostMapping("/send")
    public String sendEmail(@RequestParam MultipartFile attachment, @ModelAttribute("newEmail") Email email) {  // Primim obiectul din view plin
        System.out.println(attachment.getName());
        emailRepository.sendEmail(email, attachment);
        return "/email/index";
    }

}
