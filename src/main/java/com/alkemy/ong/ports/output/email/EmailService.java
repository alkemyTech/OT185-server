package com.alkemy.ong.ports.output.email;

import com.alkemy.ong.domain.model.Organization;
import com.alkemy.ong.domain.model.User;

public interface EmailService {
	void sendWelcomeEmail(User toUser, Organization organization);
}
