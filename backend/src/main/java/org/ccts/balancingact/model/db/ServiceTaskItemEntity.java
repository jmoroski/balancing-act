package org.ccts.balancingact.model.db;

import java.math.BigDecimal;

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

    ServiceTaskItemEntity() {}

    public BigDecimal getAmount() {
        return item.getAmount();
    }

    public String getDescription() {
        return item.getDescription();
    }

    public BigDecimal getQuantity() {
        return item.getQuantity();
    }

    public BigDecimal getRate() {
        return item.getRate();
    }

    public boolean isCalculated() {
        return item.isCalculated();
    }

    public ServiceTaskEntity getServiceTask() {
        return serviceTask;
    }

    public void setServiceTask(ServiceTaskEntity serviceTask) {
        this.serviceTask = serviceTask;
    }

    public ItemDetails getItem() {
        return item;
    }
}
