package com.alkemy.ong.ports.output.email;

import static com.alkemy.ong.ports.output.email.EmailTemplateValues.FIRST_NAME;
import static com.alkemy.ong.ports.output.email.EmailTemplateValues.ORGANIZATION_ADDRESS;
import static com.alkemy.ong.ports.output.email.EmailTemplateValues.ORGANIZATION_EMAIL;
import static com.alkemy.ong.ports.output.email.EmailTemplateValues.ORGANIZATION_FACEBOOK;
import static com.alkemy.ong.ports.output.email.EmailTemplateValues.ORGANIZATION_IMAGE;
import static com.alkemy.ong.ports.output.email.EmailTemplateValues.ORGANIZATION_INSTAGRAM;
import static com.alkemy.ong.ports.output.email.EmailTemplateValues.ORGANIZATION_LINKEDIN;
import static com.alkemy.ong.ports.output.email.EmailTemplateValues.ORGANIZATION_NAME;
import static com.alkemy.ong.ports.output.email.EmailTemplateValues.ORGANIZATION_PHONE;
import static com.alkemy.ong.ports.output.email.EmailTemplateValues.SUBJECT;
import static com.alkemy.ong.ports.output.email.EmailTemplateValues.WELCOME_SUBJECT;
import static com.alkemy.ong.ports.output.email.EmailTemplateValues.WELCOME_TEXT;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.alkemy.ong.domain.model.Contact;
import com.alkemy.ong.domain.model.Organization;
import com.alkemy.ong.domain.model.User;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import static com.alkemy.ong.ports.output.email.EmailTemplateValues.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendGridEmailService implements EmailService {

	private final SendGrid client;

	@Value("${email.from}")
	private String from;

	@Value("${email.welcome-template}")
	private String welcomeTemplate;
	
	@Value("${email.contact-template}")
	private String contactTemplate;

	private Email fromEmail;

	@PostConstruct
	protected void init() {
		this.fromEmail = new Email(this.from);
	}

	@Async
	@Override
	public void sendWelcomeEmail(User toUser, Organization organization) {

		Email emailTo = new Email(toUser.getEmail());
		Mail mail = new Mail();
		mail.setFrom(fromEmail);
		mail.setTemplateId(welcomeTemplate);
		Personalization personalization = addPersonalization(organization);
		personalization.addDynamicTemplateData(FIRST_NAME, toUser.getFirstName());
		personalization.addDynamicTemplateData(SUBJECT, WELCOME_SUBJECT);
		personalization.addDynamicTemplateData(WELCOME_TEXT, organization.getWelcomeText());
		personalization.addTo(emailTo);
		mail.addPersonalization(personalization);
		sendMail(mail);
	}

	@Override
	public void sendContactedSuccessfully(Contact contact, Organization organization) {
		Email emailTo = new Email(contact.getEmail());
		Mail mail = new Mail();
		mail.setFrom(fromEmail);
		mail.setTemplateId(contactTemplate);
		Personalization personalization = addPersonalization(organization);
		personalization.addDynamicTemplateData(FIRST_NAME, contact.getName());
		personalization.addDynamicTemplateData(CONTACT_TEXT, CONTACT_TEXT_CONTENT);
		personalization.addDynamicTemplateData(SUBJECT, CONTACT_SUBJECT);
		personalization.addTo(emailTo);
		mail.addPersonalization(personalization);
		sendMail(mail);
	}
	
	@Override
	public void sendMail(Mail mail) {
		try {
			Request request = new Request();
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			client.api(request);
		} catch (IOException ex) {
			log.error("Error sending email", ex);
			throw new RuntimeException("Error sending email due " + ex);
		}
		
	}

	private Personalization addPersonalization(Organization organization) {
		Personalization personalization = new Personalization();
		personalization.addDynamicTemplateData(ORGANIZATION_NAME, organization.getName());
		personalization.addDynamicTemplateData(ORGANIZATION_ADDRESS, organization.getAddress());
		personalization.addDynamicTemplateData(ORGANIZATION_IMAGE, organization.getImage());
		personalization.addDynamicTemplateData(ORGANIZATION_PHONE, organization.getPhone());
		personalization.addDynamicTemplateData(ORGANIZATION_EMAIL, organization.getEmail());
		personalization.addDynamicTemplateData(ORGANIZATION_INSTAGRAM, organization.getInstagramUrl());
		personalization.addDynamicTemplateData(ORGANIZATION_LINKEDIN, organization.getLinkedinUrl());
		personalization.addDynamicTemplateData(ORGANIZATION_FACEBOOK, organization.getFacebookUrl());
		return personalization;
	}

	
}
