package com.company.dao;

import com.company.builder.Email;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class EmailDAO {

    private List<Email> listOfEmailsSent;
    int emailCount;

    {
        listOfEmailsSent = new ArrayList<>();
    }

    public List<Email> showListOfEmailSent() {
        return listOfEmailsSent;
    }

    public void save(Email email) {
        email.setId(++emailCount);
        listOfEmailsSent.add(email);
    }

    public void sendEmail(Email email) {
        final String username = "artur.frimu2@gmail.com";
        final String password = "Az-59491";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
            message.setSubject(email.getSubject());
            message.setText(email.getContent());
            Transport.send(message);
            System.out.println("Message was sent");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
