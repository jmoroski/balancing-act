package org.ccts.balancingact.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "bank_accounts", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"bank_id", "user_id", "name"}, name = "uk_bank_accounts")
})
public class BankAccountEntity extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "bank_id", foreignKey = @ForeignKey(name="fk_bank_accounts_banks_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BankEntity bank;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name="fk_bank_accounts_users_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;

    @Column(nullable = false)
    private String name;

    BankAccountEntity() {}

    public BankAccountEntity(final UserEntity user, final BankEntity bank) {
        this(user, bank, "Basic Checking");
    }

    public BankAccountEntity(UserEntity user, BankEntity bank, String name) {
        this.user = user;
        this.bank = bank;
        this.name = name;
    }

    public UserEntity getUser() {
        return user;
    }
}
