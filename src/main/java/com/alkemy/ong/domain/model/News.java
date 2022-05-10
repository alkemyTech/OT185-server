package com.alkemy.ong.domain.model;


import com.alkemy.ong.domain.model.audit.Audit;
import com.alkemy.ong.domain.model.audit.AuditListener;
import com.alkemy.ong.domain.model.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name="news")
@ToString
@NoArgsConstructor
@Where(clause = "is_active=true")
@SQLDelete(sql = "UPDATE news SET is_active=false WHERE news_id=?")
@EntityListeners(AuditListener.class)

public class News implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="news_id")
    private Long id;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @Column(name="content", nullable = false, updatable = false)
    private String content;

    @Column(name = "image", nullable = false, updatable = false)
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "category_id", nullable = false, updatable = false)
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "news")
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<>();

    @Embedded
    private Audit audit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(id, news.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

}
