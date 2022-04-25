package org.exemple.project_one.entities;

public interface Auditable {
    AuditFields getAuditFields();
    void setAuditFields(AuditFields auditFields);
}
