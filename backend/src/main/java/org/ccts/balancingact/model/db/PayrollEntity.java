package org.ccts.balancingact.model.db;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "payroll")
public class PayrollEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Embedded
    private ContactInfoEntity contactInfo;

    PayrollEntity() {}

    public PayrollEntity(final String name, final ContactInfoEntity contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }
}
