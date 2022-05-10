package com.alkemy.ong.ports.input.rs.controller;


import com.alkemy.ong.domain.model.Contact;
import com.alkemy.ong.domain.usecase.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.alkemy.ong.ports.input.rs.api.ApiConstants.CONTACTS_URI;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping(CONTACTS_URI)
@RequiredArgsConstructor
public class ContactController {


    private final ContactService contactService;




	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Contact>> getContacts() {
		List<Contact> list = contactService.getList();
		return ResponseEntity.ok().body(list);
	}

}
