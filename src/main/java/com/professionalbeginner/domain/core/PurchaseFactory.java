package com.professionalbeginner.domain.core;

import com.professionalbeginner.domain.core.validator.PurchaseValidator;

import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkNotNull;

public class PurchaseFactory {


    private final PurchaseValidator validator;

    public PurchaseFactory(PurchaseValidator validator) {
        this.validator = checkNotNull(validator);
    }

    public Purchase make(long id, String productType, LocalDateTime expires, Details purchaseDetails) {
        Purchase purchase = new Purchase(validator);

        purchase.setId(id);
        purchase.setProductType(productType);
        purchase.setExpires(expires);
        purchase.setPurchaseDetails(purchaseDetails);

        return purchase;
    }

}
