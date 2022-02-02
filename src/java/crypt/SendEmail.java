/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypt;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class is used to send an email to the user who has requested a password
 * reset.
 *
 * @author Alain Lozano, Ilia Consuegra
 */
public class SendEmail {

    //This is the attribute to define from where the mail is sent
    static final String FROM = "appsy.noreply@gmail.com";
    //This is the attributte to define the name of the person sending the e-mail
    static final String FROMNAME = "Appsy";
    //This is the attribute to define where we are going to send the mail
    static String to = null;
    //This is the attribute to define the user from where is the email going to send
    static final String SMTP_USERNAME = new EncriptDecript().decryptEmailUser();
    //This is the attribute to define the password for the user that is going to send the email
    final static String SMTP_PASSWORD = new EncriptDecript().decryptEmailPassword();
    //This is the attributte to define the host to send the email
    static final String HOST = "smtp.gmail.com";
    //This is the attribute to define the port to send the email
    static final int PORT = 587;
    //This is the attributo to define the subject of the email send
    static final String SUBJECT = "Password reset notification";

    /**
     * This is the method to send the email
     *
     * @param emailTo the email address where we are going to send the mail to
     * @param password the new password of the user
     * @param login the user login
     * @throws Exception
     */
    public static void sendEmail(String emailTo, String password, String login, Integer emailType) throws Exception {
        String BODYRESET = "We confirm that your password has been successfully reset."
                + "<p>These are your new login credentials:"
                + "<p>&emsp; username: " + login
                + "<p>&emsp; password: " + password;
        String BODYCHANGE = "We confirm that your password has been successfully changed.";
        to = emailTo;
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(SUBJECT);
        switch (emailType) {
            case 1:
                msg.setContent(BODYRESET, "text/html");
                break;
            case 2:
                msg.setContent(BODYCHANGE, "text/html");
                break;
        }
        Transport transport = session.getTransport("smtp");
        try {
            System.out.println("Sending...");
            System.out.println(SMTP_USERNAME + SMTP_PASSWORD);
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        } finally {
            transport.close();
        }
    }

}

