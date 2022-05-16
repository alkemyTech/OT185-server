package com.alkemy.ong.domain.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class TestimonialList extends PageImpl<Testimonial> {

    public TestimonialList(List<Testimonial> content, Pageable pageable, long total){
        super(content, pageable, total);
    }

}
