package com.alkemy.ong.domain.model;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class ContactList extends PageImpl<Contact> {

	public ContactList(List<Contact> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}

}
