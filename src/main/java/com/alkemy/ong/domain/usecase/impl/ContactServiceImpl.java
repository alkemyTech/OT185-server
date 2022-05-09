package com.alkemy.ong.domain.usecase.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alkemy.ong.domain.model.Contact;
import com.alkemy.ong.domain.repository.ContactRepository;
import com.alkemy.ong.domain.usecase.ContactService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

	private final ContactRepository contactJpaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Contact> getList() {
		return (List<Contact>) contactJpaRepository.findAll();
	}

}
