package com.alkemy.ong.ports.output.email;

public interface EmailService {
	void sendWelcomeEmail(String to, String firstName);
}
