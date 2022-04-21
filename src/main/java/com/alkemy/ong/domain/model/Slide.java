package com.alkemy.ong.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    @ToString.Exclude
    private Organization organization;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slide slide = (Slide) o;
        return Objects.equals(id, slide.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
