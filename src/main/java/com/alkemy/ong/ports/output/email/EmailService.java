package com.alkemy.ong.ports.output.email;

public interface EmailService {
    void sendText(String to,  String subject, String body);
    void sendHTML(String to,  String subject, String body);
}
