package org.ccts.balancingact.model.db;

import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payroll_task_items")
public class PayrollTaskItemEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "payroll_task_id", nullable = false, foreignKey = @ForeignKey(name = "fk_payroll_task_items_payroll_tasks_id"))
    private PayrollTaskEntity payrollTask;

    @Embedded
    private ItemDetails item;

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

    public PayrollTaskEntity getPayrollTask() {
        return payrollTask;
    }

    public void setPayrollTask(PayrollTaskEntity payrollTask) {
        this.payrollTask = payrollTask;
    }
}
