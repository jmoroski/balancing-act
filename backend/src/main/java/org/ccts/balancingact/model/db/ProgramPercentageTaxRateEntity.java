package org.ccts.balancingact.model.db;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PERCENT")
public class ProgramPercentageTaxRateEntity extends ProgramTaxRateEntity {
    @Column
    private float rate;
}
