package com.professionalbeginner.domain.core;

import com.google.common.base.MoreObjects;
import com.professionalbeginner.domain.application.DetailsDTO;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Domain object representing a purchase
 */
public class Purchase {

    private long id;
    private String productType;
    private LocalDateTime expires;
    private Details purchaseDetails;

    Purchase() {

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

    public Details getPurchaseDetails() {
        return purchaseDetails;
    }

    public void setPurchaseDetails(Details purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return id == purchase.id &&
                Objects.equals(productType, purchase.productType) &&
                Objects.equals(expires, purchase.expires) &&
                Objects.equals(purchaseDetails, purchase.purchaseDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productType, expires, purchaseDetails);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("productType", productType)
                .add("expires", expires)
                .add("purchaseDetails", purchaseDetails)
                .toString();
    }
}
