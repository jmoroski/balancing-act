package org.ccts.balancingact.model.db;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ItemDetails {
    @Column(nullable = false)
    private String description;

    @Column
    private BigDecimal quantity;

    @Column
    private BigDecimal rate;

    @Column
    private BigDecimal amount;

    ItemDetails() {}

    public static ItemDetails create(final String description, final BigDecimal quantity, final BigDecimal rate) {
        ItemDetails details = new ItemDetails();

        details.description = description;
        details.quantity = quantity;
        details.rate = rate;

        return details;
    }

    public static ItemDetails create(final String description, final BigDecimal amount) {
        ItemDetails details = new ItemDetails();

        details.description = description;
        details.amount = amount;

        return details;
    }

    public BigDecimal getAmount() {
        return amount != null ? amount : rate.multiply(quantity) ;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public boolean isCalculated() {
        return amount == null;
    }
}
