package org.ccts.balancingact.model.db;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "user_invoices")
public class UserInvoiceEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_invoices_users_id"))
    private UserEntity user;

    @Column(nullable = false)
    private LocalDate invoiceDate;

    @OneToMany(mappedBy = "userInvoice")
    @Cascade(value = CascadeType.PERSIST)
    private List<UserInvoiceItemEntity> items = new ArrayList<>();

    @Column(nullable = false)
    private String serviceName;

    @Embedded
    private ContactInfoEntity contactInfo;

    UserInvoiceEntity() {}

    public UserInvoiceEntity(final UserEntity user, final LocalDate invoiceDate) {
        this.user = user;
        this.invoiceDate = invoiceDate;
    }

    public List<UserInvoiceItemEntity> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(final ItemDetails itemDetails) {
        this.items.add(UserInvoiceItemEntity.createUserInvoiceItem(this, itemDetails));
    }

    public UserEntity getUser() {
        return user;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ContactInfoEntity getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfoEntity contactInfo) {
        this.contactInfo = contactInfo;
    }
}
