/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

//import com.mysql.cj.protocol.Message;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.InternetAddress;
//import sun.plugin2.message.transport.Transport;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author rihem
 */
public class Mailing {
//    public static void sendEmailWithAttachment(String to, String subject, String body, String attachmentPath)
//            throws MessagingException, IOException {
//Properties props = new Properties();
//props.put("mail.smtp.auth", "true");
//props.put("mail.smtp.starttls.enable", "true");
//props.put("mail.smtp.host", "smtp.gmail.com");
//props.put("mail.smtp.port", "587");
//
//// Create a Session object with the SMTP server details and authentication information
//Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//    protected PasswordAuthentication getPasswordAuthentication() {
//        return new PasswordAuthentication("your_email@gmail.com", "your_password");
//    }
//});
//    }
     public static void sendMail(String recepient) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");
        //Your gmail address
        String myAccountEmail = "rilbagra@gmail.com";
        //Your gmail password
         String password = "lavachequirit";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });


        //Prepare email message
        MimeMessage message = prepareMessage(session, myAccountEmail, recepient);

        //Send mail
        
        Transport.send((MimeMessage) message);
        System.out.println("Message sent successfully");
    }

   private static MimeMessage prepareMessage(Session session, String myAccountEmail, String recepient) {
    try {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recepient));
        message.setSubject("My First Email from Java App");
        String htmlCode = "<h1> WE LOVE JAVA </h1> <br/> <h2><b>Next Line </b></h2>";
        message.setContent(htmlCode, "text/html");
        return message;
    } catch (Exception ex) {
        Logger.getLogger(Mailing.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
}

private void sendEmailToLivreur(String email, String subject, String body, String fileName) {
            String host = "smtp.gmail.com";
            String username = "mootez202@gmail.com";
            String password = "ppbifuhwieebcjbg";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                message.setSubject(subject);
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText(body);
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(fileName);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(new File(fileName).getName());
                multipart.addBodyPart(messageBodyPart);
                message.setContent(multipart);
                Transport.send(message);
                System.out.println("Email sent to: " + email);
            } catch (MessagingException ex) {
                System.out.println("Failed to send email to: " + email);
                ex.printStackTrace();
            }

        }

    
}
