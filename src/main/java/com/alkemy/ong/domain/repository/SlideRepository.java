package com.alkemy.ong.domain.repository;

import com.alkemy.ong.domain.model.Slide;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface SlideRepository extends PagingAndSortingRepository<Slide, Long> {
    Optional<Slide> findByOrder(Integer order);

    @Query("SELECT s FROM slide as s WHERE s.organization.id = :id ORDER BY s.order")
    List<Slide> getListByOrganizationId(Long id);
}
