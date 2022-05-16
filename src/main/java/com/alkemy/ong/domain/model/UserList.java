package com.alkemy.ong.domain.model;

import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class UserList extends PageImpl<User> {
	public UserList(List<User> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}
}
