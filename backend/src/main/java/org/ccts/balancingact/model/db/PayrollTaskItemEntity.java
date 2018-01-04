package org.ccts.balancingact.model.db;

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
}
