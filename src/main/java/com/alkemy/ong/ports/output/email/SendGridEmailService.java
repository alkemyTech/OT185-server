package com.alkemy.ong.ports.output.email;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
@Slf4j
public class SendGridEmailService implements EmailService {

    private final SendGrid sendGridClient;


    public SendGridEmailService(SendGrid sendGridClient) {
        this.sendGridClient = sendGridClient;

    }

    @Override

    public void sendText(String from, String subject, String body) {
        Response response = sendEmail(from,  subject, new Content("text/plain", body));
        log.info("Status Code: " + response.getStatusCode() + ", Body: " + response.getBody() + ", Headers: "
                + response.getHeaders());

    }

    @Override
    public void sendHTML(String from,  String subject, String body) {
        Response response = sendEmail(from, subject, new Content("text/html", body));
        log.info("Status Code: " + response.getStatusCode() + ", Body: " + response.getBody() + ", Headers: "
                + response.getHeaders());
    }

    private Response sendEmail(String from,  String subject, Content content){
        Mail mail = new Mail(new Email(from), subject, new Email(), content);
        mail.setReplyTo(new Email("somosfundacionmas@gmail.com"));
        Request request = new Request();
        Response response = null;
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            this.sendGridClient.api(request);
        } catch (IOException ex) {
            log.error("Error sending email", ex);
            throw new RuntimeException("Error sending email due " + ex);
        }
        return response;
    }
}
