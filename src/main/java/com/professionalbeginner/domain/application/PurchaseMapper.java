package com.professionalbeginner.domain.application;

import com.professionalbeginner.domain.core.Details;
import com.professionalbeginner.domain.core.Purchase;
import com.professionalbeginner.domain.core.PurchaseFactory;

/**
 * Mapper for Details
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
        Details details = detailsMapper.transform(purchaseDTO.purchaseDetails);
        return factory.make(purchaseDTO.id, purchaseDTO.productType, purchaseDTO.expires, details);
    }
}
