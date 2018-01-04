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

import org.ccts.balancingact.model.common.TaskFrequency;

@Entity
@Table(name = "payroll_tasks")
public class PayrollTaskEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "payroll_id", nullable = false, foreignKey = @ForeignKey(name = "fk_payroll_tasks_payroll_id"))
    private PayrollEntity payroll;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private TaskFrequency frequency;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date endDate;
}
