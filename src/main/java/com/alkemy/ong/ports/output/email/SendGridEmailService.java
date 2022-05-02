package com.alkemy.ong.ports.output.email;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendGridEmailService implements EmailService {

    private final SendGrid client;

    @Value("${email.from}")
    private String from;

    private Email fromEmail;

    @PostConstruct
    protected void init() {
        this.fromEmail = new Email(this.from);
    }

    @Override
    public void sendText(String to, String subject, String body) {
        sendEmail(to, subject, new Content("text/plain", body));
    }

    @Override
    public void sendHTML(String to, String subject, String body) {
        sendEmail(to, subject, new Content("text/html", body));
    }

    private void sendEmail(String to, String subject, Content content) {
        Mail mail = new Mail(fromEmail, subject, new Email(to), content);
        mail.setReplyTo(fromEmail);
        try {
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            this.client.api(request);
        } catch (IOException ex) {
            log.error("Error sending email", ex);
            throw new RuntimeException("Error sending email due " + ex);
        }
    }
}
