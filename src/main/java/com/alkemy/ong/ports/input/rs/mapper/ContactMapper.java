package com.alkemy.ong.ports.input.rs.mapper;

import com.alkemy.ong.domain.model.Contact;
import com.alkemy.ong.ports.input.rs.request.CreateContactRequest;
import org.mapstruct.Mapper;

@Mapper
public interface ContactMapper {
    Contact createContactRequestToContact(CreateContactRequest createContactRequest);
}
