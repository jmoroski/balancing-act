package org.ccts.balancingact.model.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    private TaskFrequency frequency;

    @Column(nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date endDate;

    public String getName() {
        return name;
    }
}
