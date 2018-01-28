package org.ccts.balancingact.model;

import java.util.List;
import java.util.stream.Collectors;

import org.ccts.balancingact.model.api.BillingRuleItem;
import org.ccts.balancingact.model.api.CalculatedBillingRuleItem;
import org.ccts.balancingact.model.api.SimpleBillingRuleItem;
import org.ccts.balancingact.model.db.ServiceTaskItemEntity;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.spi.MappingContext;

public class ModelMapperUtils {
    public static final ModelMapper getInstance() {
        final ModelMapper instance = new ModelMapper();
        instance.getConfiguration()
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(AccessLevel.PRIVATE);
        instance.createTypeMap(ServiceTaskItemEntity.class, BillingRuleItem.class)
            .include(ServiceTaskItemEntity.class, CalculatedBillingRuleItem.class).setCondition(new Condition<ServiceTaskItemEntity, CalculatedBillingRuleItem>() {
                @Override
                public boolean applies(MappingContext<ServiceTaskItemEntity, CalculatedBillingRuleItem> context) {
                    return context.getSource().isCalculated();
                }
            })
            .include(ServiceTaskItemEntity.class, SimpleBillingRuleItem.class).setCondition(new Condition<ServiceTaskItemEntity, SimpleBillingRuleItem>() {
                @Override
                public boolean applies(MappingContext<ServiceTaskItemEntity, SimpleBillingRuleItem> context) {
                    return !context.getSource().isCalculated();
                }
            });

        return instance;
    }

    public static final <T> List<T> mapList(final List<?> source, Class<T> destClass) {
        final ModelMapper mapper = getInstance();

        return source.stream().map(item -> mapper.map(item, destClass)).collect(Collectors.toList());
    }
}
