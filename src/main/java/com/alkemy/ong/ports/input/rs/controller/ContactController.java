package com.alkemy.ong.ports.input.rs.controller;

import static com.alkemy.ong.ports.input.rs.api.ApiConstants.CONTACTS_URI;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.ong.domain.model.Contact;
import com.alkemy.ong.domain.usecase.ContactService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(CONTACTS_URI)
@RequiredArgsConstructor
public class ContactController {

	private final ContactService contactService;

	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<Contact>> getContacts() {
		List<Contact> list = contactService.getList();
		return ResponseEntity.ok().body(list);
	}
}
