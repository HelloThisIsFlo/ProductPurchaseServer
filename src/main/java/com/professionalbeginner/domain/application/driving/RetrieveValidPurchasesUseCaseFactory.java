package com.professionalbeginner.domain.application.driving;

import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.DetailsMapper;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.PurchaseMapper;
import com.professionalbeginner.domain.application.driven.PurchaseDetailsRepository;
import com.professionalbeginner.domain.application.driven.PurchaseRepository;
import com.professionalbeginner.domain.application.driven.PurchaseSerializer;
import com.professionalbeginner.domain.core.Details;
import com.professionalbeginner.domain.core.Purchase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Factory to create the 'RetrieveValidPurchasesUseCase' at the desired time.
 */
public class RetrieveValidPurchasesUseCaseFactory {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;
    private final PurchaseDetailsRepository detailsRepository;
    private final DetailsMapper detailsMapper;
    private final PurchaseSerializer serializer;

    public RetrieveValidPurchasesUseCaseFactory(PurchaseRepository purchaseRepository,
                                                PurchaseMapper purchaseMapper,
                                                PurchaseDetailsRepository detailsRepository,
                                                DetailsMapper detailsMapper,
                                                PurchaseSerializer serializer) {
        this.purchaseRepository = checkNotNull(purchaseRepository);
        this.purchaseMapper = checkNotNull(purchaseMapper);
        this.detailsRepository = checkNotNull(detailsRepository);
        this.detailsMapper = checkNotNull(detailsMapper);
        this.serializer = checkNotNull(serializer);
    }

    public RetrieveValidPurchasesUseCase make(LocalDateTime currentTime) {
        return new RetrieveValidPurchasesUseCase(
                purchaseRepository,
                purchaseMapper,
                detailsRepository,
                detailsMapper,
                serializer,
                currentTime
        );
    }

}
