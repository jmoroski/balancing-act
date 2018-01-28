package org.ccts.balancingact.dao;

import java.util.List;
import java.util.UUID;

import org.ccts.balancingact.model.api.PayrollCompany;
import org.ccts.balancingact.model.api.PayrollRule;
import org.ccts.balancingact.model.api.PayrollRuleItem;

public interface PayrollDao {
    List<PayrollCompany> getPayrollCompanies();
    PayrollCompany addPayrollCompany(PayrollCompany payrollCompany);
    PayrollCompany updatePayrollCompany(PayrollCompany payrollCompany);
    void removePayrollCompany(UUID id);
    PayrollCompany getPayrollCompany(UUID id);
    List<PayrollRule> getPayrollRules(UUID payrollCompanyId);
    PayrollRule getPayrollRule(UUID payrollRuleId);
    PayrollRule addPayrollRule(PayrollRule payrollRule);
    PayrollRule updatePayrollRule(PayrollRule payrollRule);
    PayrollRuleItem addPayrollRuleItem(UUID payrollRuleId, PayrollRuleItem payrollRuleItem);
    void removePayrollRule(UUID id);
}
