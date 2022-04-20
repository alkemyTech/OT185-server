package com.alkemy.ong.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Objects;
import com.alkemy.ong.domain.model.audit.Audit;
import com.alkemy.ong.domain.model.audit.AuditListener;
import com.alkemy.ong.domain.model.audit.Auditable;



    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @Entity(name = "testimonial")
    @Where(clause = "is_active=true")
    @SQLDelete(sql = "UPDATE testimonial SET is_active=false WHERE testimonial_id=?")
    @EntityListeners(AuditListener.class)
    public class Testimonial implements Auditable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "testimonial_id")
        private Long id;

        @Column(name = "name", nullable = false)
        private String name;

        @Column(name = "image", nullable = false)
        private String image;

        @Column(name = "content")
        private String content;


        @Embedded
        private Audit audit;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Testimonial testimonial = (Testimonial) o;
            return Objects.equals(id, testimonial.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}