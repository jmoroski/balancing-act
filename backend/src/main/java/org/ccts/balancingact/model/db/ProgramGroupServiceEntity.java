package org.ccts.balancingact.model.db;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "program_group_services")
public class ProgramGroupServiceEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "program_group_id", nullable = false, foreignKey = @ForeignKey(name = "fk_program_group_services_program_groups_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProgramGroupEntity programGroup;

    @ManyToOne
    @JoinColumn(name = "service_rule_id", nullable = false, foreignKey = @ForeignKey(name = "fk_program_group_services_service_rules_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ServiceTaskEntity serviceRule;

    ProgramGroupServiceEntity() {}

    public ProgramGroupServiceEntity(final ProgramGroupEntity programGroup, final ServiceTaskEntity serviceRule) {
        this.programGroup = programGroup;
        this.serviceRule = serviceRule;
    }
}

