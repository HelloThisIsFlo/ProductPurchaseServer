package com.professionalbeginner.data.detail.jpa;

import com.google.common.base.MoreObjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Created by Florian on 13/06/16.
 */
@Entity
public class DetailJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long domainId;
    private String description;
    private int quantity;
    private double value;

    public DetailJPA() {
    }

    public DetailJPA(long domainId, String description, int quantity, double value) {
        this.domainId = domainId;
        this.description = description;
        this.quantity = quantity;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public long getDomainId() {
        return domainId;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getValue() {
        return value;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDomainId(long domainId) {
        this.domainId = domainId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("domainId", domainId)
                .add("description", description)
                .add("quantity", quantity)
                .add("value", value)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailJPA jpa = (DetailJPA) o;
        return id == jpa.id &&
                domainId == jpa.domainId &&
                quantity == jpa.quantity &&
                Double.compare(jpa.value, value) == 0 &&
                Objects.equals(description, jpa.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, domainId, description, quantity, value);
    }
}
