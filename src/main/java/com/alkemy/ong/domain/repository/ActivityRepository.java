package com.alkemy.ong.domain.repository;

import com.alkemy.ong.domain.model.Activity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ActivityRepository extends PagingAndSortingRepository<Activity, Long> {
}
