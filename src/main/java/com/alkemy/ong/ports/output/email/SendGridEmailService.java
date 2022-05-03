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
import com.alkemy.ong.domain.model.Organization;
import com.alkemy.ong.domain.usecase.OrganizationService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendGridEmailService implements EmailService {

	private final SendGrid client;

	private final OrganizationService organizationService;

	private static final Long ORGANIZATION_ID = (long) 1;

	@Value("${email.from}")
	private String from;

	@Value("${email.template}")
	private String templateId;

	private Email fromEmail;

	@PostConstruct
	protected void init() {
		this.fromEmail = new Email(this.from);
	}

	@Async
	@Override
	public void sendWelcomeEmail(String to, String firstName) {

		Organization org = organizationService.getByIdIfExists(ORGANIZATION_ID);

		Email emailTo = new Email(to);

		Mail mail = new Mail();
		mail.setFrom(fromEmail);
		mail.setTemplateId(templateId);

		Personalization personalization = new Personalization();
		personalization.addDynamicTemplateData(FIRST_NAME, firstName);
		personalization.addDynamicTemplateData(ORGANIZATION_NAME, org.getName());
		personalization.addDynamicTemplateData(WELCOME_TEXT, org.getWelcomeText());
		personalization.addDynamicTemplateData(ORGANIZATION_ADDRESS, org.getAddress());
		personalization.addDynamicTemplateData(ORGANIZATION_IMAGE, org.getImage());
		personalization.addDynamicTemplateData(ORGANIZATION_PHONE, org.getPhone());
		personalization.addDynamicTemplateData(ORGANIZATION_EMAIL, org.getEmail());
		personalization.addDynamicTemplateData(ORGANIZATION_INSTAGRAM, org.getInstagramUrl());
		personalization.addDynamicTemplateData(ORGANIZATION_LINKEDIN, org.getLinkedinUrl());
		personalization.addDynamicTemplateData(ORGANIZATION_FACEBOOK, org.getFacebookUrl());
		personalization.addDynamicTemplateData(SUBJECT, WELCOME_SUBJECT);
		personalization.addTo(emailTo);

		mail.addPersonalization(personalization);

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
}
