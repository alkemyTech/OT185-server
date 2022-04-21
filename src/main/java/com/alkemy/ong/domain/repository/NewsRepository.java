package com.alkemy.ong.domain.repository;

import com.alkemy.ong.domain.model.News;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NewsRepository extends PagingAndSortingRepository<News, Long> {
}
