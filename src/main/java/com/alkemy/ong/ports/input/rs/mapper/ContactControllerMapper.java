package com.alkemy.ong.ports.input.rs.mapper;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import com.alkemy.ong.domain.model.Contact;
import com.alkemy.ong.ports.input.rs.response.ContactResponse;

@Mapper
public interface ContactControllerMapper extends CommonMapper {

	@IterableMapping(qualifiedByName = "contactToContactResponse")
	List<ContactResponse> contactListToContactResponseList(List<Contact> contacts);

	@Named("contactToContactResponse")
	ContactResponse contactToContactResponse(Contact contact);

}
