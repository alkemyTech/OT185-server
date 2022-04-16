package com.alkemy.ong.domain.repository;

import com.alkemy.ong.domain.model.entity.ContactEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<ContactEntity, Long> {
}
