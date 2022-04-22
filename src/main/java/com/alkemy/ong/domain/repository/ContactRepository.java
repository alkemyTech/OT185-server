package com.alkemy.ong.domain.repository;

import com.alkemy.ong.domain.model.Contact;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContactRepository extends PagingAndSortingRepository<Contact,Long> {
}
