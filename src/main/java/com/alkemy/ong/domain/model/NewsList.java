package com.alkemy.ong.domain.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class NewsList extends PageImpl<News> {
    public NewsList(List<News> content, Pageable pageable, long total) {super(content, pageable, total);}
}
