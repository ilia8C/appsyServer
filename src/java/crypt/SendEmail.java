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
 *
 * @author Alain Lozano, Ilia Consuegra
 */
public class SendEmail {

    // Replace sender@example.com with your "From" address.
    // This address must be verified.
    static final String FROM = "appsy.noreply@gmail.com";
    static final String FROMNAME = "Appsy";

    // Replace recipient@example.com with a "To" address. If your account 
    // is still in the sandbox, this address must be verified.
    static String to = null;

    // Replace smtp_username with your Amazon SES SMTP user name.
    static final String SMTP_USERNAME = "appsy.noreply@gmail.com";

    // Replace smtp_password with your Amazon SES SMTP password.
    static final String SMTP_PASSWORD = "abcd*1234";

    // The name of the Configuration Set to use for this message.
    // If you comment out or remove this variable, you will also need to
    // comment out or remove the header below.
    //static final String CONFIGSET = "ConfigSet";
    // Amazon SES SMTP host name. This example uses the US West (Oregon) region.
    // See https://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html#region-endpoints
    // for more information.
    static final String HOST = "smtp.gmail.com";

    // The port you will connect to on the Amazon SES SMTP endpoint. 
    static final int PORT = 25;

    static final String SUBJECT = "Password reset notification";

    static final String BODY = "We confirm that your password has been successfully reset.\n"
            + "These are your new login credentials:\n"
            + "username:\n"
            + "password:";

    public static void sendEmail(String emailTo) throws Exception {
        to = emailTo;
        // Create a Properties object to contain connection configuration information.
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        // Create a Session object to represent a mail session with the specified properties. 
        Session session = Session.getDefaultInstance(props);

        // Create a message with the specified information. 
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(SUBJECT);
        msg.setContent(BODY, "text/html");

        // Add a configuration set header. Comment or delete the 
        // next line if you are not using a configuration set
        //msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
        // Create a transport.
        Transport transport = session.getTransport("smtp");

        // Send the message.
        try {
            System.out.println("Sending...");

            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        } finally {
            // Close and terminate the connection.
            transport.close();
        }
    }
}
