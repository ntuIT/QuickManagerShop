package com.qman.web.utility;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailUtility {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtility.class);

    /*
     * sendEmail via Gmail host provider
     * @param String email - the receiver email
     */
    public static void sendEmail(String email, String content, String subject) {
        final String username = "quytm2239@gmail.com";
        final String password = "Quytm2391993";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() { return new PasswordAuthentication(username, password); }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject, "UTF-8");// 2nd param is the encoding
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.addHeader("content-type", "text/html");// set to
                                                                   // html
                                                                   // format
            messageBodyPart.setText(content, "UTF-8"); // 2nd param is the
                                                       // encoding
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            LOGGER.debug("[START] sending email to " + message.getRecipients(RecipientType.TO));
            Transport.send(message);
            LOGGER.debug("[FINISH] sending email to " + message.getRecipients(RecipientType.TO));
        } catch (MessagingException e) {
            LOGGER.debug("[FINISH] sending email with error");
            e.printStackTrace();
        }
    }
}