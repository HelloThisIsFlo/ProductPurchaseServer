package com.professionalbeginner.domain.application.driving;

import com.professionalbeginner.domain.application.driven.PurchaseDetailsRepository;
import com.professionalbeginner.domain.application.driven.PurchaseRepository;
import com.professionalbeginner.domain.application.driven.PurchaseSerializer;
import com.professionalbeginner.domain.core.validator.PurchaseValidator;

/**
 *
 */
public class RetrieveValidPurchasesUseCase implements UseCase<String> {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseDetailsRepository detailsRepository;
    private final PurchaseValidator validator;
    private final PurchaseSerializer serializer;

    public RetrieveValidPurchasesUseCase(PurchaseRepository purchaseRepository,
                                         PurchaseDetailsRepository detailsRepository,
                                         PurchaseValidator validator, PurchaseSerializer serializer) {
        this.purchaseRepository = purchaseRepository;
        this.detailsRepository = detailsRepository;
        this.validator = validator;
        this.serializer = serializer;
    }

    @Override
    public void execute(OnSuccessCallback<String> onSuccessCallback) {


    }
}
