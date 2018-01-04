package org.ccts.balancingact.model.db;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "service_task_items")
public class ServiceTaskItemEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "service_task_id", nullable = false, foreignKey = @ForeignKey(name = "fk_service_task_items_service_tasks_id"))
    private ServiceTaskEntity serviceTask;

    @Embedded
    private ItemDetails item;
}
