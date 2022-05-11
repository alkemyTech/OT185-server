package com.alkemy.ong.ports.output.email;

import com.alkemy.ong.domain.model.Contact;
import com.alkemy.ong.domain.model.Organization;
import com.alkemy.ong.domain.model.User;
import com.sendgrid.helpers.mail.Mail;

public interface EmailService {
	void sendWelcomeEmail(User toUser, Organization organization);
	void sendContactedSuccessfully(Contact contact, Organization organization);
	void sendMail(Mail mail);
}
