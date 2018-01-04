package org.ccts.balancingact.model.db;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_invoices")
public class UserInvoiceEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_invoices_users_id"))
    private UserEntity user;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date invoiceDate;

    @OneToMany(mappedBy = "userInvoice")
    private List<UserInvoiceItemEntity> items;
}
