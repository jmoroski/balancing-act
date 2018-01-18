package org.ccts.balancingact.dao;

import java.util.List;
import java.util.UUID;

import org.ccts.balancingact.model.api.BankAccountSummary;
import org.ccts.balancingact.model.api.BankAccountTransaction;

public interface BankingDao {
    List<BankAccountSummary> getAllAccountBalances();
    List<BankAccountTransaction> getAccountTransactions(UUID accountId);
    BankAccountSummary getAccount(UUID accountId);
    BankAccountTransaction addAccountTransaction(BankAccountTransaction transaction);
    BankAccountTransaction updateAccountTransaction(BankAccountTransaction transaction);
    void removeTransaction(UUID transactionId);

    UUID getBankId();
    void createBankAccount(UUID userId, UUID bankId);
}
