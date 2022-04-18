package com.alkemy.ong.domain.model;

import com.alkemy.ong.domain.model.audit.Audit;
import com.alkemy.ong.domain.model.audit.AuditListener;
import com.alkemy.ong.domain.model.audit.Auditable;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "organization")
@Where(clause = "is_active=true")
@SQLDelete(sql = "UPDATE organization SET is_active=false WHERE organization_id=?")
@EntityListeners(AuditListener.class)
public class Organization implements Auditable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "organization_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private Integer phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "welcomeText", nullable = false )
    @Type(type="text")
    private String welcomeText;

    @Column(name = "aboutUsText")
    @Type(type="text")
    private String aboutUsText;

    @Column(name = "start_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    @Embedded
    private Audit audit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization organization = (Organization) o;
        return Objects.equals(id, organization.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
