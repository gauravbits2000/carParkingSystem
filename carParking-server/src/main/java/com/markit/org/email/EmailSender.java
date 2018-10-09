package com.markit.org.email;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Class for emailing results of calling the service
 *
 */
public class EmailSender {
   
    private String mailTo = null;
    private String mailFrom = null;
    private String smtpHost = null;
    private String senderHost = null;
    private MimeMessage message;
    private StringBuilder sb = new StringBuilder("");
    private static Properties properties = null;
    private Session session = null;
    private final String br = System.getProperty("line.separator");
    private EmailStatus status;
    private boolean sendHtmlEmail = false;

    private String parsingType;
    private String parsingDate;

    public EmailSender(String mailTo) {
        // default the email status is successful
        // if any part of the parsing process fail, it will return an failure email
        status = EmailStatus.SUCCESS;
        mailFrom = "hari.gupta@ihsmarkit.com";
        smtpHost = "uksmtp.markit.partners";

        try {
            InetAddress addr = InetAddress.getLocalHost();
            senderHost = addr.getHostName();
        } catch (UnknownHostException ignored) {}

        // Get system properties
        properties = System.getProperties();
        properties.setProperty("mail.smtp.host", smtpHost);

        session = Session.getDefaultInstance(properties);

        try{
            // Create a default MimeMessage object.
            message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(mailFrom));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));

        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public void setSubject(String subject) {
        // Set Subject: header field
        try {
            message.setSubject(subject);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setSendHtmlEmail(boolean sendAsHtml) {
        this.sendHtmlEmail=sendAsHtml;
    }

    public EmailSender appendContent(String content) {
        this.sb.append(content);
        return this;
    }

    public boolean sendEmail() {

        // message too big
        if (sb.length() > 5000000) {
            this.setSubject("[DataParser Status] - Email too big.");
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("ERROR_EMAIL_TOO_BIG_LOGGED" + "\n\n\n----------\n\n"
                    + "WARNING: ONLY THE FIRST 5 M LOG IN THIS EMAIL.\n\n\n");
            errorMessage.append(sb.substring(0, 5000000));
            // replace the existing message object by modified 5M message
            sb = errorMessage;
        }

        try {
            if(sendHtmlEmail) {
                // Send the messages as HTML/web pages
                message.setContent(sb.toString(), "text/html");
            } else {
                message.setText(sb.toString());
            }
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Something went wrong; return false to let the
            // calling method handle it (this shouldn't happen
            // often) This is messaging exception - generic could-not-reach server
            // error, at which point telnet into mailhost on port 25 (from webserver),
            // dig out the old "helo" etc SMTP commands and check it can send mails.
            // However, we don't expect these errors in the normal run of events
            // Be carefull not to resend the message if this returns false!
            return false;
        }
        return true; // everything worked
    }



    public EmailSender appendSeparator() {
        this.sb.append("\n");
        this.sb.append("------------------------------------------------\n");
        this.sb.append("\n");
        return this;
    }

    public EmailSender newLine() {
        this.sb.append("\n");
        return this;
    }

    public String getParsingDate() {
        return parsingDate;
    }

    public void setParsingDate(String parsingDate) {
        this.parsingDate = parsingDate;
    }

    public String getParsingType() {
        return parsingType;
    }

    public void setParsingType(String parsingType) {
        this.parsingType = parsingType;
    }

    public EmailStatus getEmailStatus() {
        return status;
    }

    public void setEmailStatus(EmailStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return sb.toString();
    }

    public String getMailTo() {
		return mailTo;
	}

    public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
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
