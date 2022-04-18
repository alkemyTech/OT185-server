package com.alkemy.ong.domain.model;

import com.alkemy.ong.domain.model.audit.Audit;
import com.alkemy.ong.domain.model.audit.AuditListener;
import com.alkemy.ong.domain.model.audit.Auditable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Objects;

@Entity(name="comment")
@Getter
@Setter
@EntityListeners(AuditListener.class)
@Where(clause = "is_active=true")
@SQLDelete(sql = "UPDATE comment SET is_active=false WHERE comment_id=?")

public class Comment implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name="comment_body")
    private String commentBody;

    @Column(name="news_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "news_id")
    private Long newsId;

    @Embedded
    private Audit audit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
