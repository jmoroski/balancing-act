package org.ccts.balancingact.model.db;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_invoice_items")
public class UserInvoiceItemEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_invoice_id", nullable = false, foreignKey = @ForeignKey(name = "user_invoice_items_user_invoices_id"))
    private UserInvoiceEntity userInvoice;

    @Embedded
    private ItemDetails item;
}
