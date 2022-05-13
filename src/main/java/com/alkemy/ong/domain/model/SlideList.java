package com.alkemy.ong.domain.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class SlideList extends PageImpl<Slide> {
    public SlideList(List<Slide> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
}
