package org.ccts.balancingact.model.db;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ItemDetails {
    @Column(nullable = false)
    private String description;

    @Column
    private int quantity;

    @Column
    private float rate;

    @Column
    private BigDecimal amount;

    ItemDetails() {}

    public static ItemDetails create(final String description, final int quantity, final float rate) {
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
        return amount != null ? amount : BigDecimal.valueOf(rate * (float) quantity) ;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getRate() {
        return rate;
    }

    public boolean isCalculated() {
        return amount == null;
    }
}
