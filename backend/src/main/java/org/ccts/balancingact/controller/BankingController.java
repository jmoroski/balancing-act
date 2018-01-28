package org.ccts.balancingact.controller;

import java.util.List;
import java.util.UUID;

import org.ccts.balancingact.dao.BankingDao;
import org.ccts.balancingact.model.api.BankAccountSummary;
import org.ccts.balancingact.model.api.BankAccountTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/banking")
public class BankingController {
    @Autowired
    private BankingDao bankingDao;

    @GetMapping(path = "/accounts")
    public ResponseEntity<List<BankAccountSummary>> getAccountsSummaries() {
        return new ResponseEntity<>(bankingDao.getAllAccountBalances(), HttpStatus.OK);
    }

    @GetMapping(path = "/accounts/{accountId}")
    public ResponseEntity<BankAccountSummary> getAccountDetails(@PathVariable final String accountId) {
        return new ResponseEntity<>(bankingDao.getAccount(UUID.fromString(accountId)), HttpStatus.OK);
    }

    @GetMapping(path = "/accounts/{accountId}/transactions")
    public ResponseEntity<List<BankAccountTransaction>> getAccountTransactions(@PathVariable final String accountId) {
        return new ResponseEntity<>(bankingDao.getAccountTransactions(UUID.fromString(accountId)), HttpStatus.OK);
    }

    @PostMapping(path = "/accounts/{accountId}/transactions")
    public ResponseEntity<BankAccountTransaction> addAccountTransaction(@RequestBody final BankAccountTransaction transaction) {
        return new ResponseEntity<>(bankingDao.addAccountTransaction(transaction), HttpStatus.CREATED);
    }

    @PutMapping(path = {
        "/accounts/{accountId}/transactions/{transactionId}",
        "/transactions/{transactionId}"
    })
    public ResponseEntity<BankAccountTransaction> updateAccountTransaction(@RequestBody final BankAccountTransaction transaction) {
        return new ResponseEntity<>(bankingDao.updateAccountTransaction(transaction), HttpStatus.OK);
    }

    @DeleteMapping(path = {
        "/accounts/{accountId}/transactions/{transactionId}",
        "/transactions/{transactionId}"
    })
    public ResponseEntity<Void> removeAccountTransaction(@PathVariable String transactionId) {
        bankingDao.removeTransaction(UUID.fromString(transactionId));
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
