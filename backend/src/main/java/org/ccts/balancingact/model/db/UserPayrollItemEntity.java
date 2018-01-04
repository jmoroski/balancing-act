package org.ccts.balancingact.model.db;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_payroll_items")
public class UserPayrollItemEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_payroll_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_payroll_items_user_payroll_id"))
    private UserPayrollEntity userPayroll;

    @Embedded
    private ItemDetails item;
}
