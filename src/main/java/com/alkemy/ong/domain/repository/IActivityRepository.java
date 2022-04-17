package com.alkemy.ong.domain.repository;

import com.alkemy.ong.domain.model.Activity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActivityRepository extends PagingAndSortingRepository<Activity, Long> {
}
