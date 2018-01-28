package org.ccts.balancingact.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.ccts.balancingact.dao.PayrollDao;
import org.ccts.balancingact.model.api.BillingRule;
import org.ccts.balancingact.model.api.BillingRuleItem;
import org.ccts.balancingact.model.api.PayrollCompany;
import org.ccts.balancingact.model.api.PayrollRule;
import org.ccts.balancingact.model.api.PayrollRuleItem;
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
@RequestMapping(path = "/api/payroll")
public class PayrollController {
    @Autowired
    private PayrollDao payrollDao;

    @GetMapping
    public ResponseEntity<List<PayrollCompany>> getPayrollCompanies() {
        return new ResponseEntity<>(this.payrollDao.getPayrollCompanies(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PayrollCompany> addPayrollCompany(@RequestBody final PayrollCompany payrollCompany) {
        return new ResponseEntity<>(this.payrollDao.addPayrollCompany(payrollCompany), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PayrollCompany> getPayrollCompany(@PathVariable UUID id) {
        return new ResponseEntity<>(this.payrollDao.getPayrollCompany(id), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PayrollCompany> updatePayrollCompany(@RequestBody final PayrollCompany payrollCompany) {
        return new ResponseEntity<>(this.payrollDao.updatePayrollCompany(payrollCompany), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePayrollCompany(@PathVariable UUID id) {
        this.payrollDao.removePayrollCompany(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/{id}/payrollRules")
    public ResponseEntity<List<PayrollRule>> getPayrollRules(@PathVariable UUID id) {
        return new ResponseEntity<>(this.payrollDao.getPayrollRules(id), HttpStatus.OK);
    }

    @PostMapping(path = "/{id}/payrollRules")
    public ResponseEntity<PayrollRule> addPayrollRule(@PathVariable UUID id, @RequestBody PayrollRule payrollRule) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = {
        "/{payrollCompanyId}/payrollRules/{id}",
        "/payrollRules/{id}"
    })
    public ResponseEntity<PayrollRule> getPayrollRule(@PathVariable(required = false) Optional<UUID> payrollCompanyId, @PathVariable UUID id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/{payrollCompanyId}/payrollRules/{id}")
    public ResponseEntity<BillingRule> updatePayrollRule(@PathVariable UUID id, @RequestBody PayrollRule payrollRule) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = {
        "/{payrollCompanyId}/payrollRules/{id}/items",
        "/payrollRules/{id}/items"
    })
    public ResponseEntity<BillingRuleItem> addPayrollRuleItem(@PathVariable(required = false) Optional<UUID> payrollCompanyId, @PathVariable UUID id, @RequestBody PayrollRuleItem payrollRuleItem) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{payrollCompanyId}/payrollRules/{id}")
    public ResponseEntity<Void> deletePayrollRule(@PathVariable UUID id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
