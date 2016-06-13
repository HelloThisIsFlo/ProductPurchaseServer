package com.professionalbeginner.domain.application;

import com.professionalbeginner.domain.core.Details;
import com.professionalbeginner.domain.core.Purchase;
import com.professionalbeginner.domain.core.PurchaseFactory;

import java.time.LocalDateTime;

import static com.google.common.base.Strings.nullToEmpty;

/**
 * Mapper for Purchase.
 */
public class PurchaseMapper {

    private final PurchaseFactory factory;
    private final DetailsMapper detailsMapper;

    public PurchaseMapper(PurchaseFactory factory, DetailsMapper detailsMapper) {
        this.factory = factory;
        this.detailsMapper = detailsMapper;
    }

    public PurchaseDTO transform(Purchase purchase) {
        DetailsDTO detailsDTO = detailsMapper.transform(purchase.getPurchaseDetails());
        return new PurchaseDTO(purchase.getId(), purchase.getProductType(), purchase.getExpires(), detailsDTO);
    }

    public Purchase transform(PurchaseDTO purchaseDTO) {

        Details details;
        if (purchaseDTO.purchaseDetails != null) {
            details = detailsMapper.transform(purchaseDTO.purchaseDetails);
        } else {
            details = Details.NULL;
        }

        LocalDateTime expires = purchaseDTO.expires;
        if (expires == null) {
            expires = LocalDateTime.MIN;
        }

        String type = nullToEmpty(purchaseDTO.productType);

        return factory.make(purchaseDTO.id, type, expires, details);
    }
}
