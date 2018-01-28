package org.ccts.balancingact.model.db;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.ccts.balancingact.model.common.TaskFrequency;

@Entity
@Table(name = "service_tasks", uniqueConstraints = {
    @UniqueConstraint(columnNames = "name", name = "uk_service_tasks")
})
public class ServiceTaskEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "fk_service_tasks_services_id"))
    private ServiceEntity service;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskFrequency frequency;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    public String getName() {
        return name;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }
}
