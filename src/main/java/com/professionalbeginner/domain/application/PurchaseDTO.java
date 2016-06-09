package com.professionalbeginner.domain.application;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

public class PurchaseDTO {

    public final long id;
    public final String productType;
    public final LocalDateTime expires;
    public final DetailsDTO purchaseDetails;

    public PurchaseDTO(long id, String productType, LocalDateTime expires, DetailsDTO purchaseDetails) {
        this.id = id;
        this.productType = checkNotNull(productType);
        this.expires = checkNotNull(expires);
        this.purchaseDetails = checkNotNull(purchaseDetails);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseDTO that = (PurchaseDTO) o;
        return id == that.id &&
                Objects.equals(productType, that.productType) &&
                Objects.equals(expires, that.expires) &&
                Objects.equals(purchaseDetails, that.purchaseDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productType, expires, purchaseDetails);
    }
}
