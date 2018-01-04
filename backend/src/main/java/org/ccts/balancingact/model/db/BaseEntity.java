package org.ccts.balancingact.model.db;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class BaseEntity implements Persistable {
    @Id
    @Column(length = 16)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @Override
    public UUID getId() {
        return this.id;
    }

    void setId(final UUID id) {
        this.id = id;
    }
}
