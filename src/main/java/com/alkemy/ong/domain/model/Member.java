package com.alkemy.ong.domain.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import com.alkemy.ong.domain.model.audit.Audit;
import com.alkemy.ong.domain.model.audit.AuditListener;
import com.alkemy.ong.domain.model.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "member")
@Where(clause = "is_active=true")
@SQLDelete(sql = "UPDATE member SET is_active=false WHERE member_id=?")
@EntityListeners(AuditListener.class)
public class Member implements Auditable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;
	
	@Column(name = "name", nullable = false, updatable = false)
    private String name;
	
	@Column(name = "facebookUrl", nullable = true, updatable = true)
    private String facebookUrl;
	
	@Column(name = "instagramUrl", nullable = true, updatable = true)
    private String instagramUrl;
	
	@Column(name = "linkedinUrl", nullable = true, updatable = true)
    private String linkedinUrl;
	
	@Column(name = "image", nullable = false, updatable = true)
    private String image;
	
	@Column(name = "description", nullable = false, updatable = true)
    private String description;
	
	@Embedded
    private Audit audit;
	
	@Override
	public boolean equals(Object o) {
		   if (this == o) return true;
		   if (o == null || getClass() != o.getClass()) return false;
		   Member member = (Member) o;
		   return Objects.equals(id, member.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	

}
