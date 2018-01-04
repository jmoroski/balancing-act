package org.ccts.balancingact.model.db;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("STUDENT")
public class StudentEntity extends UserEntity {
    StudentEntity() {}

    public StudentEntity(final String firstName, final String lastName) {
        super(firstName, lastName);
    }

    @Override
    public String getUserType() {
        return "Student";
    }
}
