package com.markit.org.email;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Class for emailing results of calling the service
 *
 */
public class EmailSender {
   
    private String mailFrom = null;
    private String smtpHost = null;
    private String senderHost = null;
    private MimeMessage message;
    private StringBuilder sb = new StringBuilder("");
    private static Properties properties = null;
    private Session session = null;
    private final String br = System.getProperty("line.separator");
    private EmailStatus status;

    public EmailSender(String mailTo, String primaryEmpName, String pooledEmpName, String quarter) {
        
        status = EmailStatus.SUCCESS;
        mailFrom = "IN-FLM@ihsmarkit.com";
        smtpHost = "uksmtp.markit.partners";

        try {
            InetAddress addr = InetAddress.getLocalHost();
            senderHost = addr.getHostName();
        } catch (UnknownHostException ignored) {}

        // Get system properties
        properties = System.getProperties();
        properties.setProperty("mail.smtp.host", smtpHost);

        session = Session.getDefaultInstance(properties);

        try {

           // Create a default MimeMessage object.
           Message message = new MimeMessage(session);

           // Set From: header field of the header.
           message.setFrom(new InternetAddress(mailFrom));

           // Set To: header field of the header.
           message.setRecipients(Message.RecipientType.TO,
              InternetAddress.parse(mailTo));

           // Set Subject: header field
           message.setSubject(quarter + " Car Parking Draw Winner");

           // This mail has 2 part, the BODY and the embedded image
           MimeMultipart multipart = new MimeMultipart("related");

           // first part (the html)
           BodyPart messageBodyPart = new MimeBodyPart();
           String htmlText = "<H1>Hello " +mailTo+ "</H1><img src=\"cid:image\">";
           messageBodyPart.setContent(htmlText, "text/html");
           // add it
           multipart.addBodyPart(messageBodyPart);

           // second part (the image)
           messageBodyPart = new MimeBodyPart();
           DataSource fds = new FileDataSource("./src/main/resources/congratulations.jpg");
                               
           messageBodyPart.setDataHandler(new DataHandler(fds));
           messageBodyPart.setHeader("Content-ID", "<image>");

           // add image to the multipart
           multipart.addBodyPart(messageBodyPart);

           // put everything together
           message.setContent(multipart);
           
           // Send message
           Transport.send(message);

           System.out.println("Sent message successfully....");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
         }
    }

	public enum EmailStatus {
        SUCCESS("OK", true), FAIL("ERROR", false), WARNING("WARNING", false);
        private String msg;
        private boolean status;

        private EmailStatus(String msg, boolean status) {
            this.msg = msg;
            this.status = status;
        }

        public String toString() {
            return msg;
        }

        public boolean status() {
            return status;
        }
    }
}
