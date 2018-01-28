package org.ccts.balancingact.model.api;

import java.math.BigDecimal;

public class InvoiceReportItem {
    private String description;
    private int quantity;
    private float rate;
    private BigDecimal amount;

    public InvoiceReportItem(String description, int quantity, float rate, BigDecimal amount) {
        this.description = description;
        this.quantity = quantity;
        this.rate = rate;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
