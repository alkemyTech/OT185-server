package com.alkemy.ong.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.alkemy.ong.domain.model.Contact;

public interface ContactRepository extends PagingAndSortingRepository<Contact,Long> {
}
