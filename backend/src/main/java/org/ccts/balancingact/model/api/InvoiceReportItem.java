package org.ccts.balancingact.model.api;

import java.math.BigDecimal;

public class InvoiceReportItem {
    private String description;
    private BigDecimal quantity;
    private BigDecimal rate;
    private BigDecimal amount;

    public InvoiceReportItem(String description, BigDecimal quantity, BigDecimal rate, BigDecimal amount) {
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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
