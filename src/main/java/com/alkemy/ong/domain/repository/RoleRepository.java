package com.alkemy.ong.domain.repository;

import com.alkemy.ong.domain.model.RoleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, Long> {
}
