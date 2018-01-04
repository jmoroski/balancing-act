package org.ccts.balancingact.config;

import org.hibernate.dialect.MySQL5Dialect;

public class CustomMySQLDialect extends MySQL5Dialect {
    @Override
    public boolean supportsCascadeDelete() {
        return true;
    }
}
