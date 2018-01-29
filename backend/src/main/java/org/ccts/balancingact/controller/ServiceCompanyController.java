package org.ccts.balancingact.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.ccts.balancingact.dao.ServiceDao;
import org.ccts.balancingact.model.api.BillingRule;
import org.ccts.balancingact.model.api.BillingRuleItem;
import org.ccts.balancingact.model.api.Service;
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
@RequestMapping(path = "/api/services")
public class ServiceCompanyController {
    @Autowired
    private ServiceDao serviceDao;

    @GetMapping
    public ResponseEntity<List<Service>> getServices() {
        return new ResponseEntity<>(serviceDao.getServices(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Service> addService(@RequestBody final Service service) {
        return new ResponseEntity<>(this.serviceDao.addService(service), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Service> updateService(@RequestBody final Service service) {
        return new ResponseEntity<>(this.serviceDao.updateService(service), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Service> getService(@PathVariable String id) {
        return new ResponseEntity<>(serviceDao.getService(UUID.fromString(id)), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable UUID id) {
        this.serviceDao.removeService(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/{id}/billingRules")
    public ResponseEntity<List<BillingRule>> getBillingRules(@PathVariable UUID id) {
        return new ResponseEntity<>(serviceDao.getBillingRules(id), HttpStatus.OK);
    }

    @PostMapping(path = "/{id}/billingRules")
    public ResponseEntity<BillingRule> addBillingRule(@PathVariable UUID id, @RequestBody BillingRule billingRule) {
        return new ResponseEntity<>(serviceDao.addBillingRule(billingRule), HttpStatus.CREATED);
    }

    @GetMapping(path = {
        "/{serviceId}/billingRules/{id}",
        "/billingRules/{id}"
    })
    public ResponseEntity<BillingRule> getBillingRule(@PathVariable(required = false) Optional<UUID> serviceId, @PathVariable UUID id) {
        return new ResponseEntity<>(serviceDao.getBillingRule(id), HttpStatus.OK);
    }

    @PutMapping(path = "/{serviceId}/billingRules/{id}")
    public ResponseEntity<BillingRule> updateBillingRule(@PathVariable UUID id, @RequestBody BillingRule billingRule) {
        return new ResponseEntity<>(serviceDao.updateBillingRule(billingRule), HttpStatus.OK);
    }

    @GetMapping(path = {
        "/{serviceId}/billingRules/{id}/items",
        "/billingRules/{id}/items"
    })
    public ResponseEntity<List<BillingRuleItem>> getBillingRuleItems(@PathVariable(required = false) Optional<UUID> serviceId, @PathVariable UUID id) {
        return new ResponseEntity<>(serviceDao.getBillingRuleItems(id), HttpStatus.OK);
    }

    @PostMapping(path = {
        "/{serviceId}/billingRules/{id}/items",
        "/billingRules/{id}/items"
    })
    public ResponseEntity<BillingRuleItem> addBillingRuleItem(@PathVariable(required = false) Optional<UUID> serviceId, @PathVariable UUID id, @RequestBody BillingRuleItem billingRuleItem) {
        return new ResponseEntity<>(serviceDao.addBillingRuleItem(id, billingRuleItem), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{serviceId}/billingRules/{id}")
    public ResponseEntity<Void> deleteBillingRule(@PathVariable UUID id) {
        this.serviceDao.removeBillingRule(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
