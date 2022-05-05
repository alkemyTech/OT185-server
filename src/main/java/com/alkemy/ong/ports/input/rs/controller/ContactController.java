package com.alkemy.ong.ports.input.rs.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.ong.domain.model.ContactList;
import com.alkemy.ong.domain.usecase.ContactService;
import com.alkemy.ong.ports.input.rs.api.ApiConstants;
import com.alkemy.ong.ports.input.rs.mapper.ContactControllerMapper;
import com.alkemy.ong.ports.input.rs.response.ContactResponse;
import com.alkemy.ong.ports.input.rs.response.ContactResponseList;
import lombok.RequiredArgsConstructor;
import static com.alkemy.ong.ports.input.rs.api.ApiConstants.CONTACTS_URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(CONTACTS_URI)
@RequiredArgsConstructor
public class ContactController {

	private final ContactService contactService;
	private final ContactControllerMapper contactMapper;

	@GetMapping
	public ResponseEntity<ContactResponseList> getContacts(@RequestParam Optional<Integer> page,
			@RequestParam Optional<Integer> size) {
		
		final int pageNumber = page.filter(p -> p > 0).orElse(ApiConstants.DEFAULT_PAGE);
		final int pageSize = size.filter(s -> s > 0).orElse(ApiConstants.DEFAULT_PAGE_SIZE);

		ContactList contactList = contactService.getList(PageRequest.of(pageNumber, pageSize));

		ContactResponseList response;
		{
			response = new ContactResponseList();
			List<ContactResponse> content = contactMapper.contactListToContactResponseList(contactList.getContent());
			response.setContent(content);

			final int nextPage = contactList.getPageable().next().getPageNumber();
			response.setNextUri(ApiConstants.uriByPageAsString.apply(nextPage));

			final int previousPage = contactList.getPageable().previousOrFirst().getPageNumber();
			response.setPreviousUri(ApiConstants.uriByPageAsString.apply(previousPage));

			response.setTotalPages(contactList.getTotalPages());
			response.setTotalElements(contactList.getTotalElements());
		}

		return ResponseEntity.ok().body(response);

	}
}
