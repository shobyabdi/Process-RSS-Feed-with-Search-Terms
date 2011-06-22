package com.resumerobot.rssparser.server;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.resumerobot.rssparser.server.ServerConstants;
import javax.activation.DataHandler;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class OutgoingMail {

	public static void sendEmailToRecipient(String body, String toName, String toAddress)
	{
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        try {
        	InternetAddress me = new InternetAddress();
        	me.setAddress(toAddress);
        	me.setPersonal(toName);
        	Multipart mp = new MimeMultipart();
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(body, "text/html");
            mp.addBodyPart(htmlPart);
            /*byte[] attachmentData;
            MimeBodyPart attachment = new MimeBodyPart();
            attachment.setFileName("manual.pdf");
            attachment.setContent(attachmentData, "application/pdf");
            mp.addBodyPart(attachment);*/
            Message msg = new MimeMessage(session);
            msg.setFrom(me);
            msg.addRecipient(Message.RecipientType.TO,me);
            msg.setSubject(ServerConstants.EMAIL_SUBJECT);
            msg.setContent(mp);
            Transport.send(msg);
        } catch (AddressException e) {
        	e.printStackTrace();
        } catch (MessagingException e) {
        	e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
