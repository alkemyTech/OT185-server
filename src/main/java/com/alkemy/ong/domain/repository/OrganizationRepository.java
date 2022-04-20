package com.alkemy.ong.domain.repository;


import com.alkemy.ong.domain.model.Organization;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Long> {
}
