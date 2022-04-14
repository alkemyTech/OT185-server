package com.alkemy.ong.domain.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AlkymerList extends PageImpl<Alkymer> {
    public AlkymerList(List<Alkymer> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
}
