package org.ccts.balancingact.model.db;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.ccts.balancingact.model.api.RuleFrequency;

@Entity
@Table(name = "payroll_tasks")
public class PayrollTaskEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "payroll_id", nullable = false, foreignKey = @ForeignKey(name = "fk_payroll_tasks_payroll_id"))
    private PayrollEntity payroll;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private RuleFrequency frequency;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @OneToMany(mappedBy = "payrollTask")
    private List<PayrollTaskItemEntity> items;

    public void setPayroll(PayrollEntity payroll) {
        this.payroll = payroll;
    }
}
