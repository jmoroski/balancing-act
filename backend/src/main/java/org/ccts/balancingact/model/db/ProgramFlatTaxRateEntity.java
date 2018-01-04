package org.ccts.balancingact.model.db;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FLAT")
public class ProgramFlatTaxRateEntity extends ProgramTaxRateEntity {
    @Column
    private BigDecimal amount;
}
