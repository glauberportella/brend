package com.biavan.brend.plugin.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public final class SendMailSSL {
	
	private static Properties props;
	private static Message message;
	private static Session session;
	private static MessageProperty messageProperty;
	private static Mailable config;
	
	public static void send(MessageProperty messageProperty, Mailable config) {
		SendMailSSL.messageProperty = messageProperty;
		SendMailSSL.config = config;
		
		setProperties();
		setSession();
		setMessage();
		
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private static void setProperties() {
		props = new Properties();
		props.put("mail.smtp.host", config.getSmtpHost());
		props.put("mail.smtp.socketFactory.port", config.getSmtpPort());
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", config.getSmtpPort());
	}
	
	private static void setSession() {
		session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(config.getSmtpLogin(), config.getSmtpSenha());
				}
			});
	}
	
	private static void setMessage() {
		try {
 
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(messageProperty.getFrom()));
			message.setSubject(messageProperty.getSubject());
			message.setText(messageProperty.getMessage());
			
			for(String to : messageProperty.getRecipients()) {
				message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(to));
			}
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}