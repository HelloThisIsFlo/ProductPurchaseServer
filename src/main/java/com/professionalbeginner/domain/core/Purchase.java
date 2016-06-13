package com.professionalbeginner.domain.core;

import com.google.common.base.MoreObjects;
import com.professionalbeginner.domain.core.validator.PurchaseValidator;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.google.common.base.Strings.nullToEmpty;

/**
 * Domain object representing a purchase
 */
public class Purchase {

    public final static Purchase NULL;

    static {
        PurchaseValidator validator = (toValidate, currentTime) -> false;
        PurchaseFactory factory = new PurchaseFactory(validator);
        NULL = factory.make(0, "", LocalDateTime.MIN, Details.NULL);
    }

    private final PurchaseValidator validator;
    private long id;
    private String productType;
    private LocalDateTime expires;
    private Details purchaseDetails;

    Purchase(PurchaseValidator validator) {
        this.validator = validator;
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
        this.productType = nullToEmpty(productType);
    }

    public LocalDateTime getExpires() {
        return expires;
    }

    public void setExpires(LocalDateTime expires) {
        this.expires = expires;
        if (this.expires == null) {
            this.expires = LocalDateTime.MIN;
        }
    }

    public Details getPurchaseDetails() {
        return purchaseDetails;
    }

    public void setPurchaseDetails(Details purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
        if (this.purchaseDetails == null) {
            this.purchaseDetails = Details.NULL;
        }
        ensureIdMatch();
    }

    private void ensureIdMatch() {
        if (!purchaseDetails.equals(Details.NULL)) {
            if (purchaseDetails.getId() != this.id) {
                throw new IllegalStateException("Details should have the same Id as associated Purchase");
            }
        }
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

    public boolean validate(LocalDateTime currentTime) {
        return validator.validForCurrentTime(this, currentTime);
    }
}
