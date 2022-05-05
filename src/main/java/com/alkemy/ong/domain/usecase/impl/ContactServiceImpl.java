package com.alkemy.ong.domain.usecase.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alkemy.ong.domain.model.Contact;
import com.alkemy.ong.domain.model.ContactList;
import com.alkemy.ong.domain.repository.ContactRepository;
import com.alkemy.ong.domain.usecase.ContactService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

	private final ContactRepository contactJpaRepository;

	@Override
	@Transactional(readOnly = true)
	public ContactList getList(PageRequest pageRequest) {
		Page<Contact> page = contactJpaRepository.findAll(pageRequest);
		return new ContactList(page.getContent(), pageRequest, page.getTotalElements());
	}
	

}
