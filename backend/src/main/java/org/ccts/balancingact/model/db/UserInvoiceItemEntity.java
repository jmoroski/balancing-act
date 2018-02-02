package org.ccts.balancingact.model.db;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "user_invoice_items")
public class UserInvoiceItemEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_invoice_id", nullable = false, foreignKey = @ForeignKey(name = "user_invoice_items_user_invoices_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserInvoiceEntity userInvoice;

    @Embedded
    private ItemDetails item;

    private UserInvoiceItemEntity() {}

    static UserInvoiceItemEntity createUserInvoiceItem(final UserInvoiceEntity userInvoice, final ItemDetails item) {
        final UserInvoiceItemEntity entity = new UserInvoiceItemEntity();

        entity.userInvoice = userInvoice;
        entity.item = item;

        return entity;
    }

    public ItemDetails getItem() {
        return item;
    }
}
