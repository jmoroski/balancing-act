package org.ccts.balancingact.model.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "users")
@DiscriminatorColumn(name = "user_type", length = 25)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class UserEntity extends BaseEntity {
    @Column(length = 50)
    @Nationalized
    private String firstName;

    @Column(length = 50)
    @Nationalized
    private String lastName;

    @Embedded
    private ContactInfoEntity contactInfo;

    @CreationTimestamp
    @Column(insertable = true, nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    private Date createdOn = new Date();

    UserEntity() {}

    protected UserEntity(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public abstract String getUserType();
}
