package org.ccts.balancingact.model;

import java.util.List;
import java.util.stream.Collectors;

import org.ccts.balancingact.model.api.BillingRuleItem;
import org.ccts.balancingact.model.api.CalculatedBillingRuleItem;
import org.ccts.balancingact.model.api.CalculatedPayrollRuleItem;
import org.ccts.balancingact.model.api.PayrollRuleItem;
import org.ccts.balancingact.model.api.SimpleBillingRuleItem;
import org.ccts.balancingact.model.api.SimplePayrollRuleItem;
import org.ccts.balancingact.model.db.PayrollTaskItemEntity;
import org.ccts.balancingact.model.db.ServiceTaskItemEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.spi.MappingContext;

public class ModelMapperUtils {
    private static final Converter<ServiceTaskItemEntity, BillingRuleItem> BILLING_RULE_ITEM_CONVERTER = new Converter<ServiceTaskItemEntity, BillingRuleItem>() {
        @Override
        public BillingRuleItem convert(MappingContext<ServiceTaskItemEntity, BillingRuleItem> context) {
            final ServiceTaskItemEntity entity = context.getSource();
            if (entity.isCalculated()) {
                final CalculatedBillingRuleItem item = new CalculatedBillingRuleItem();
                item.setDescription(entity.getDescription());
                item.setId(entity.getId().toString());
                item.setQuantity(entity.getQuantity());
                item.setRate(entity.getRate());

                return item;
            } else {
                final SimpleBillingRuleItem item = new SimpleBillingRuleItem();
                item.setDescription(entity.getDescription());
                item.setId(entity.getId().toString());
                item.setAmount(entity.getAmount());

                return item;
            }
        }
    };

    private static final Converter<PayrollTaskItemEntity, PayrollRuleItem> PAYROLL_RULE_ITEM_CONVERTER = new Converter<PayrollTaskItemEntity, PayrollRuleItem>() {
        @Override
        public PayrollRuleItem convert(MappingContext<PayrollTaskItemEntity, PayrollRuleItem> context) {
            final PayrollTaskItemEntity entity = context.getSource();
            if (entity.isCalculated()) {
                final CalculatedPayrollRuleItem item = new CalculatedPayrollRuleItem();
                item.setDescription(entity.getDescription());
                item.setId(entity.getId().toString());
                item.setQuantity(entity.getQuantity());
                item.setRate(entity.getRate());

                return item;
            } else {
                final SimplePayrollRuleItem item = new SimplePayrollRuleItem();
                item.setDescription(entity.getDescription());
                item.setId(entity.getId().toString());
                item.setAmount(entity.getAmount());

                return item;
            }
        }
    };
    public static final ModelMapper getInstance() {
        final ModelMapper instance = new ModelMapper();
        instance.getConfiguration()
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(AccessLevel.PRIVATE);
        instance.addConverter(BILLING_RULE_ITEM_CONVERTER);
        instance.addConverter(PAYROLL_RULE_ITEM_CONVERTER);

        return instance;
    }

    public static final <T> List<T> mapList(final List<?> source, Class<T> destClass) {
        final ModelMapper mapper = getInstance();

        return source.stream().map(item -> mapper.map(item, destClass)).collect(Collectors.toList());
    }
}
