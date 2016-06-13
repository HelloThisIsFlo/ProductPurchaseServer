package com.professionalbeginner.domain.application.driving;

import com.professionalbeginner.domain.application.DetailsMapper;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.PurchaseMapper;
import com.professionalbeginner.domain.application.driven.PurchaseDetailsRepository;
import com.professionalbeginner.domain.application.driven.PurchaseRepository;

public class SaveOrUpdateUseCaseFactory {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseDetailsRepository detailsRepository;
    private final PurchaseMapper purchaseMapper;
    private final DetailsMapper detailsMapper;

    public SaveOrUpdateUseCaseFactory(PurchaseRepository purchaseRepository, PurchaseDetailsRepository detailsRepository,
                                      PurchaseMapper purchaseMapper, DetailsMapper detailsMapper) {
        this.purchaseRepository = purchaseRepository;
        this.detailsRepository = detailsRepository;
        this.purchaseMapper = purchaseMapper;
        this.detailsMapper = detailsMapper;
    }

    public SaveOrUpdateUseCase make(PurchaseDTO purchase) {
        return new SaveOrUpdateUseCase(purchase, purchaseRepository, detailsRepository, purchaseMapper, detailsMapper);
    }
}
