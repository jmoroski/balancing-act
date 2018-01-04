package org.ccts.balancingact.model.db;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class AdministratorEntity extends UserEntity {
    AdministratorEntity() {}

    public AdministratorEntity(final String firstName, final String lastName) {
        super(firstName, lastName);
    }

    @Override
    public String getUserType() {
        return "Administrator";
    }
}
