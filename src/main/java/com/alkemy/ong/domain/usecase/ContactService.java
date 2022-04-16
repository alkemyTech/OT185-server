package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.request.ContactRequestDTO;
import com.alkemy.ong.domain.model.response.ContactResponseDTO;

import java.util.List;

public interface ContactService {

    List<ContactResponseDTO> getContacts();

    ContactResponseDTO saveContact(ContactRequestDTO request);
}
