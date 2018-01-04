package org.ccts.balancingact.model.db;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContactInfoEntity {
    @Column(length = 50)
    private String address1;

    @Column(length = 50)
    private String address2;

    @Column(length = 35)
    private String city;

    @Column(length = 2)
    private String state;

    @Column(length = 10)
    private String zip;

    ContactInfoEntity() {}

    public static ContactInfoEntity create(final String address1, final String address2, final String city, final String state, final String zip) {
        ContactInfoEntity entity = new ContactInfoEntity();
        entity.address1 = address1;
        entity.address2 = address2;
        entity.city = city;
        entity.state = state;
        entity.zip = zip;

        return entity;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
