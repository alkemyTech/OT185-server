package com.alkemy.ong.domain.usecase;

import com.alkemy.ong.domain.model.Alkymer;
import com.alkemy.ong.domain.model.AlkymerList;
import org.springframework.data.domain.PageRequest;

public interface AlkymerService {
    Long createEntity(Alkymer entity);
    void deleteById(Long id);
    Alkymer getByIdIfExists(Long id);
    AlkymerList getList(PageRequest pageRequest);
    void updateEntityIfExists(Long id, Alkymer entity);
}
