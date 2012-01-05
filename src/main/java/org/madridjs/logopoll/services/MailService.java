package org.madridjs.logopoll.services;

public interface MailService {

	

	void send(String from, String to, String subject, String body);

}
