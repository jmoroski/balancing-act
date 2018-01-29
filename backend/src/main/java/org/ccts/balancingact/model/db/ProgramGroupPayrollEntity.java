package org.ccts.balancingact.model.db;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "program_group_payroll")
public class ProgramGroupPayrollEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "program_group_id", nullable = false, foreignKey = @ForeignKey(name = "fk_program_group_payroll_program_groups_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProgramGroupEntity programGroup;

    @ManyToOne
    @JoinColumn(name = "payroll_rule_id", nullable = false, foreignKey = @ForeignKey(name = "fk_program_group_payroll_payroll_rules_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PayrollTaskEntity payrollRule;

    ProgramGroupPayrollEntity() {}

    public ProgramGroupPayrollEntity(final ProgramGroupEntity programGroup, final PayrollTaskEntity payrollRule) {
        this.programGroup = programGroup;
        this.payrollRule = payrollRule;
    }
}
