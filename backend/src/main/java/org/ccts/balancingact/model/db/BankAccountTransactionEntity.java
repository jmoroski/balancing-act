package org.ccts.balancingact.model.db;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "bank_account_transactions")
public class BankAccountTransactionEntity extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", foreignKey = @ForeignKey(name = "fk_bank_account_transaction_bank_accounts_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BankAccountEntity account;

    @Column(nullable = false)
    private LocalDate transactionTime;

    @Column(nullable = false, precision = 9, scale = 2)
    private BigDecimal amount;

    @Column
    private String name;

    BankAccountTransactionEntity() { }

    public BankAccountTransactionEntity(final BankAccountEntity account, final LocalDate transactionTime, final BigDecimal amount) {
        this(account, transactionTime, amount, null);
    }

    public BankAccountTransactionEntity(final BankAccountEntity account, final LocalDate transactionTime, final BigDecimal amount, final String name) {
        this.account = account;
        this.transactionTime = transactionTime;
        this.amount = amount;
        this.name = name;
    }

    public LocalDate getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDate transactionTime) {
        this.transactionTime = transactionTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BankAccountEntity getAccount() {
        return account;
    }

    public void setAccount(BankAccountEntity account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
