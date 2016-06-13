package com.professionalbeginner.data.purchase.jpa;

import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.core.Details;
import org.springframework.stereotype.Component;

@Component
public class JpaPurchaseMapper {

    PurchaseJPA transform(PurchaseDTO purchaseDTO) {

        long id = 0;
        if (purchaseDTO.id > 0) {
            id = purchaseDTO.id;
        }
        return new PurchaseJPA(
                id,
                purchaseDTO.productType,
                purchaseDTO.expires
        );
    }

    PurchaseDTO transform(PurchaseJPA purchaseJPA) {
        if (purchaseJPA == null) {
            return PurchaseDTO.NULL;
        }
        return new PurchaseDTO(
                purchaseJPA.getId(),
                purchaseJPA.getProductType(),
                purchaseJPA.getExpires(),
                DetailsDTO.NULL
        );
    }
}
