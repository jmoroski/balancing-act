package org.ccts.balancingact.dao;

import java.util.List;
import java.util.UUID;

import org.ccts.balancingact.model.api.BillingRule;
import org.ccts.balancingact.model.api.BillingRuleItem;
import org.ccts.balancingact.model.api.Service;

public interface ServiceDao {
    List<Service> getServices();
    Service getService(UUID id);
    List<BillingRule> getBillingRules(UUID serviceId);
    BillingRule getBillingRule(UUID billingRuleId);
    BillingRule addBillingRule(BillingRule billingRule);
    BillingRule updateBillingRule(BillingRule billingRule);
    BillingRuleItem addBillingRuleItem(UUID billingRuleId, BillingRuleItem billingRuleItem);
    void removeBillingRule(UUID id);
    Service addService(Service service);
    Service updateService(Service service);
    void removeService(UUID id);
}
