package com.alkemy.ong.domain.usecase.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alkemy.ong.domain.model.Contact;
import com.alkemy.ong.domain.model.Organization;
import com.alkemy.ong.domain.repository.ContactRepository;
import com.alkemy.ong.domain.repository.OrganizationRepository;
import com.alkemy.ong.domain.usecase.ContactService;
import com.alkemy.ong.ports.output.email.SendGridEmailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

	private final ContactRepository contactJpaRepository;
	
	private final OrganizationRepository organizationJpaRepository;
	
	private final SendGridEmailService emailService;
	
	private static final Long ORGANIZATION_ID = (long) 1;

	@Override
	@Transactional(readOnly = true)
	public List<Contact> getList() {
		return (List<Contact>) contactJpaRepository.findAll();
	}

	@Override
	@Transactional
	public Long createEntity(Contact contact) {
		Optional<Organization> organization = organizationJpaRepository.findById(ORGANIZATION_ID);
		if(organization.isPresent()) {
			emailService.sendContactedSuccessfully(contact, organization.get());
		}
		return contactJpaRepository.save(contact).getId();
	}

}
