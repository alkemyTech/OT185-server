package com.alkemy.ong.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "slide")
public class Slide {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "slide_id")
    private Long id;

    @Column(name = "imageUrl")
    private String imageUrl;
    @Column(name = "text")
    private String text;
    @Column(name = "number")
    private Integer number;

    @ManyToOne
    @JoinTable(name = "slide_organization",
            joinColumns = @JoinColumn(name = "slide_id"),
            inverseJoinColumns = @JoinColumn(name = "organization_id"))
    @ToString.Exclude
    private Organization organization;
}
