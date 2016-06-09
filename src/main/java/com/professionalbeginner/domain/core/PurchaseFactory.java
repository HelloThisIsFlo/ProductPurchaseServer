package com.professionalbeginner.domain.core;

import java.time.LocalDateTime;

public class PurchaseFactory {

    public Purchase make(long id, String productType, LocalDateTime expires, Details purchaseDetails) {
        Purchase purchase = new Purchase();

        purchase.setId(id);
        purchase.setProductType(productType);
        purchase.setExpires(expires);
        purchase.setPurchaseDetails(purchaseDetails);

        return purchase;
    }

}
