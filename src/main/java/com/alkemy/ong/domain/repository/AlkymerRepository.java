package com.alkemy.ong.domain.repository;

import com.alkemy.ong.domain.model.Alkymer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlkymerRepository extends PagingAndSortingRepository<Alkymer, Long> {
}
