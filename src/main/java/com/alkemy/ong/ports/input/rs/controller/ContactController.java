package com.alkemy.ong.ports.input.rs.controller;


import com.alkemy.ong.domain.model.Contact;
import com.alkemy.ong.domain.usecase.ContactService;
import com.alkemy.ong.ports.input.rs.mapper.ContactMapper;
import com.alkemy.ong.ports.input.rs.request.CreateContactRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static com.alkemy.ong.ports.input.rs.api.ApiConstants.CONTACTS_URI;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;


@RestController
@RequestMapping(CONTACTS_URI)
@RequiredArgsConstructor
public class ContactController {


    private final ContactService contactService;
	private final ContactMapper mapper;

	@PostMapping
	public ResponseEntity<Void> createContact(@Valid @RequestBody CreateContactRequest createContactRequest) {

		Contact contact = mapper.createContactRequestToContact(createContactRequest);

		final long id = contactService.createEntity(contact);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(id)
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Contact>> getContacts() {
		List<Contact> list = contactService.getList();
		return ResponseEntity.ok().body(list);
	}

}
