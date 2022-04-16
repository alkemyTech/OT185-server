package com.alkemy.ong.domain.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name="news")
@SQLDelete(sql = "UPDATE news SET deleted=true WHERE id=?")

public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="alkymer_id")
    Long id;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @Column(name = "image", nullable = false, updatable = false)
    private String image;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Long categoryId;

    @Column(name="date")
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());



}
