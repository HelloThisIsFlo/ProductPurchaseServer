package com.professionalbeginner.serialisation;

import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.driven.PurchaseSerializer;

import java.util.List;

/**
 * Mock Serializer that uses the toString representation.
 */
public class ToStringMockSerializer implements PurchaseSerializer{

    @Override
    public String serializeAll(List<PurchaseDTO> purchase) {
        if (purchase == null) {
            return "";
        }
        return serializeNonNull(purchase);
    }

    private String serializeNonNull(List<PurchaseDTO> purchase) {

        StringBuilder builder = new StringBuilder();
        for (PurchaseDTO purchaseDTO : purchase) {
            builder.append(purchaseDTO.toString());
            builder.append("\n");
        }

        return builder.toString().trim();
    }

}
