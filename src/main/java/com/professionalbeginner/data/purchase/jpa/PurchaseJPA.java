package com.professionalbeginner.data.purchase.jpa;

import com.google.common.base.MoreObjects;
import com.professionalbeginner.domain.application.DetailsDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class PurchaseJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String productType;
    private LocalDateTime expires;

    public PurchaseJPA() {
    }

    public PurchaseJPA(long id, String productType, LocalDateTime expires) {
        this.id = id;
        this.productType = productType;
        this.expires = expires;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public LocalDateTime getExpires() {
        return expires;
    }

    public void setExpires(LocalDateTime expires) {
        this.expires = expires;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseJPA jpa = (PurchaseJPA) o;
        return id == jpa.id &&
                Objects.equals(productType, jpa.productType) &&
                Objects.equals(expires, jpa.expires);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productType, expires);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("productType", productType)
                .add("expires", expires)
                .toString();
    }
}
