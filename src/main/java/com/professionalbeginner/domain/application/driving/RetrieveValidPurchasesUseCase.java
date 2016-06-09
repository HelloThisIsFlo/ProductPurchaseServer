package com.professionalbeginner.domain.application.driving;

import com.professionalbeginner.domain.application.DetailsMapper;
import com.professionalbeginner.domain.application.PurchaseMapper;
import com.professionalbeginner.domain.application.driven.PurchaseDetailsRepository;
import com.professionalbeginner.domain.application.driven.PurchaseRepository;
import com.professionalbeginner.domain.application.driven.PurchaseSerializer;
import com.professionalbeginner.domain.core.validator.PurchaseValidator;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class RetrieveValidPurchasesUseCase implements UseCase<String> {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;
    private final PurchaseDetailsRepository detailsRepository;
    private final DetailsMapper detailsMapper;
    private final PurchaseValidator validator;
    private final PurchaseSerializer serializer;

    public RetrieveValidPurchasesUseCase(PurchaseRepository purchaseRepository,
                                         PurchaseMapper purchaseMapper,
                                         PurchaseDetailsRepository detailsRepository,
                                         DetailsMapper detailsMapper,
                                         PurchaseValidator validator,
                                         PurchaseSerializer serializer) {
        this.purchaseRepository = checkNotNull(purchaseRepository);
        this.purchaseMapper = checkNotNull(purchaseMapper);
        this.detailsRepository = checkNotNull(detailsRepository);
        this.detailsMapper = checkNotNull(detailsMapper);
        this.validator = checkNotNull(validator);
        this.serializer = checkNotNull(serializer);
    }

    @Override
    public void execute(OnSuccessCallback<String> onSuccessCallback) {


    }
}
