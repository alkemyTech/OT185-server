package com.alkemy.ong.domain.model.audit;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class AuditListener {

    @PrePersist
    public void setCreatedOn(Auditable auditable) {
        Audit audit = auditable.getAudit();

        if(audit == null) {
            audit = new Audit();
            auditable.setAudit(audit);
        }

        audit.setCreatedAt(LocalDateTime.now());
    }

    @PreUpdate
    @PreRemove
    public void setUpdadtedOn(Auditable auditable) {
        Audit audit = auditable.getAudit();

        audit.setUpdatedAt(LocalDateTime.now());
    }
}
