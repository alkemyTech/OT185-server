package com.alkemy.ong.domain.repository;

import com.alkemy.ong.domain.model.ContactEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContactRepository extends PagingAndSortingRepository<ContactEntity,Long> {
}
