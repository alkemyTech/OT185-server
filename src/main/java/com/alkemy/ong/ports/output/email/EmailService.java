package com.alkemy.ong.ports.output.email;

public interface EmailService {
    void sendText(String from,  String subject, String body);
    void sendHTML(String from,  String subject, String body);
}
