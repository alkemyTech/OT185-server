package com.alkemy.ong.domain.usecase.impl;

import com.alkemy.ong.domain.model.entity.ContactEntity;
import com.alkemy.ong.domain.model.mapper.ContactMapper;
import com.alkemy.ong.domain.model.request.ContactRequestDTO;
import com.alkemy.ong.domain.model.response.ContactResponseDTO;
import com.alkemy.ong.domain.repository.ContactRepository;
import com.alkemy.ong.domain.usecase.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    private final ContactMapper contactMapper;


    @Override
    public List<ContactResponseDTO> getContacts() {
        return contactRepository.findAll().stream()
                .map(contactMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContactResponseDTO saveContact(ContactRequestDTO request) {
        ContactEntity newContact = contactMapper.toEntity(request);
        ContactEntity savedContact = contactRepository.save(newContact);
        return contactMapper.toDto(savedContact);
    }
}
