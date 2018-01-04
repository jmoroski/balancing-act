package org.ccts.balancingact.model.db;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "bank_account_transactions")
public class BankAccountTransactionEntity extends BaseEntity {
    @ManyToOne(optional = false, cascade = { CascadeType.REMOVE })
    @JoinColumn(name = "account_id", foreignKey = @ForeignKey(name = "fk_bank_account_transaction_bank_accounts_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BankAccountEntity account;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date transactionTime;

    @Column(nullable = false, precision = 9, scale = 2)
    private BigDecimal amount;

    @Column
    private String name;

    BankAccountTransactionEntity() { }

    public BankAccountTransactionEntity(final BankAccountEntity account, final Date transactionTime, final BigDecimal amount) {
        this(account, transactionTime, amount, null);
    }

    public BankAccountTransactionEntity(final BankAccountEntity account, final Date transactionTime, final BigDecimal amount, final String name) {
        this.account = account;
        this.transactionTime = transactionTime;
        this.amount = amount;
        this.name = name;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
