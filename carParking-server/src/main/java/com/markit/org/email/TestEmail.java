package com.markit.org.email;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.markit.org.email.EmailSender.EmailStatus;



public class TestEmail {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sendEmail();

	}

	/**
	 * 
	 */
	public static void sendEmail() {
		//String mailTo = "hari.gupta@ihsmarkit.com,gaurav.agarwal@ihsmarkit.com,parag.garg@ihsmarkit.com,nikhil.chitranshi@markit.com";
		
		String mailTo = "parag.garg@ihsmarkit.com";
		
		String[] mailToArray = mailTo.split(",");
		
		/*for(String mailId : mailToArray) {
			EmailSender emailSender = new EmailSender(mailId);
		}*/
		
		/*emailSender.setEmailStatus(EmailStatus.SUCCESS);
		emailSender.setSubject("Parking Allocation Draw");
		String hostName = "";
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException ex) {
		}

		emailSender.appendContent(EmailStatus.SUCCESS.toString() + " : Host " + hostName);
		emailSender.newLine();

		emailSender.appendContent("Parking Details");

		emailSender.sendEmail();*/
	}

}
