package org.ccts.balancingact.model.db;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "services", uniqueConstraints = {
    @UniqueConstraint(columnNames = "name", name = "uk_services")
})
public class ServiceEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Embedded
    private ContactInfoEntity contactInfo;
}
