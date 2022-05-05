package com.alkemy.ong.domain.usecase;

import org.springframework.data.domain.PageRequest;
import com.alkemy.ong.domain.model.ContactList;

public interface ContactService {
	ContactList getList(PageRequest pageRequest);
}
