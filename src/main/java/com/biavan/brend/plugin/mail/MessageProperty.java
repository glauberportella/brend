package com.biavan.brend.plugin.mail;

import java.util.ArrayList;
import java.util.List;

public class MessageProperty {

	public String from;
	public List<String> recipients = new ArrayList<String>();
	public String subject;
	public String message;
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public List<String> getRecipients() {
		return recipients;
	}
	
	public void addRecipient(String recipient) {
		this.recipients.add(recipient);
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
}
