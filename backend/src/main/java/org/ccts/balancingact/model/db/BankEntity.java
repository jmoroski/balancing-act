package org.ccts.balancingact.model.db;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "banks", uniqueConstraints = {
    @UniqueConstraint(columnNames = "name", name = "uk_banks")
})
public class BankEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Embedded
    private ContactInfoEntity contactInfo;

    BankEntity() {}

    public BankEntity(final String name) {
        this.name = name;
    }
}
