package com.alkemy.ong.domain.model.audit;

public interface Auditable {
    Audit getAudit();
    void setAudit(Audit audit);
}
