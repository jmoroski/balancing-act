package org.ccts.balancingact.model.db;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tax_type", length = 25)
@Table(name = "program_tax_rates")
public abstract class ProgramTaxRateEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false, foreignKey = @ForeignKey(name = "fk_program_tax_rates_programs_id"))
    private ProgramEntity program;

    @Column(nullable = false)
    private String name;
}
