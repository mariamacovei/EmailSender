package com.company.repository;

import com.company.model.Email;
import org.apache.commons.mail.util.MimeMessageParser;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.File;

@Component
public class EmailRepository {
    public final String username = "exempleuser007@gmail.com";
    public final String password = "Anonim_007";

    public void sendEmail(Email email, MultipartFile file) {

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
            message.setSubject(email.getSubject());

            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText(email.getContent());

            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            DataSource source = new FileDataSource(new File(file.getOriginalFilename()));
            byte[] sourceBytes = file.getBytes();
            OutputStream sourceOS = source.getOutputStream();
            sourceOS.write(sourceBytes);

            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setDisposition(MimeBodyPart.ATTACHMENT);
            messageBodyPart2.setFileName(file.getOriginalFilename());
            //create Multipart object and add MimeBodyPart objects to this object

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart1);
            multipart.addBodyPart(messageBodyPart2);
            //set the multiplart object to the message object
            message.setContent(multipart);


            Transport.send(message);
            System.out.println("Message was sent");

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Email> readInbox() {
        List<Email> listOfMessege = new ArrayList<>();
        Properties props = new Properties();

        try {
            props.load(new FileInputStream(new File("C:\\config.properties")));
            Session session = Session.getDefaultInstance(props, null);

            Store store = session.getStore("imaps");
            store.connect("smtp.gmail.com", username, password);

            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                listOfMessege.add(new Email(i, message.getFrom()[0].toString(), message.getSubject(), readContent((MimeMessage) message)));
            }

            inbox.close(true);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfMessege;
    }

    public String readContent(MimeMessage message) throws Exception {
        return new MimeMessageParser(message).parse().getPlainContent();
    }
}
