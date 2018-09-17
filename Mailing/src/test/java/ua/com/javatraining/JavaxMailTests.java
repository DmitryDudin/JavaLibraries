package ua.com.javatraining;

import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class JavaxMailTests {
//    https://myaccount.google.com/lesssecureapps  -  disable gmail secure

    /*Send email from a printer, scanner, or app
        https://support.google.com/a/answer/176600?hl=en*/
    /*Sending Emails with Java:
        https://www.baeldung.com/java-email*/

    /*Setting Definitions:
        From Name: Friendly name to display to the customer
        From Email: Reply-to address
        SMTP Host: Server that will send the email
        SMTP Port: Port to connect to the email server
        Use SSL: Encrypt the email when sent to the email server (usually a different port)
        SMTP Auth: Method for authentication (almost always Login)
        Username: Login is typically the full email address (Example: mailbox@yourdomain.com)
        Password: Password is typically the same as the password to retrieve the email.*/


    @Test
    public void shoulSendSimpleMail() {
        String from = "mail from";
        String password = "password";
        String subject = "Enter subject here";
        String messageText = "enter message text here";
        String toEmail = "to mail";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
//        props.put("mail.debug", "true");
//        props.put("mail.smtp.starttls.enable", "true");//for 587 port
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");


        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);

            //add attachment
            Multipart multipart = new MimeMultipart();
            File file = new File("report.xlsx");
            /*first variant
            BodyPart bodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(file);
            bodyPart.setDataHandler(new DataHandler(source));
            bodyPart.setFileName("report.xlsx");
            bodyPart.setText(messageText);
            multipart.addBodyPart(bodyPart);
            message.setContent(multipart);*/

            //second variant
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(file);
            multipart.addBodyPart(attachmentBodyPart);

            //send mail
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
