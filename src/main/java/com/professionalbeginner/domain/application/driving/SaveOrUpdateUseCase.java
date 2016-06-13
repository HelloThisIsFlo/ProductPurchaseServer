package com.professionalbeginner.domain.application.driving;

import com.professionalbeginner.domain.application.DetailsMapper;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.PurchaseMapper;
import com.professionalbeginner.domain.application.driven.DetailsRepository;
import com.professionalbeginner.domain.application.driven.PurchaseRepository;
import com.professionalbeginner.domain.core.Details;
import com.professionalbeginner.domain.core.Purchase;

/**
 * Use case to save or Update purchases in the database.
 * The Purchase and its associated Details will be persisted to 2 separate repositories.
 *
 * If an id is provided on the Details, it will be overridden with the Purchase Id.
 */
public class SaveOrUpdateUseCase implements UseCase<Long> {

    private final PurchaseDTO toSaveOrUpdate;

    private final PurchaseRepository purchaseRepository;
    private final DetailsRepository detailsRepository;
    private final PurchaseMapper purchaseMapper;
    private final DetailsMapper detailsMapper;

    public SaveOrUpdateUseCase(PurchaseDTO toSaveOrUpdate, PurchaseRepository purchaseRepository,
                               DetailsRepository detailsRepository,
                               PurchaseMapper purchaseMapper, DetailsMapper detailsMapper) {
        this.toSaveOrUpdate = toSaveOrUpdate;
        this.purchaseRepository = purchaseRepository;
        this.detailsRepository = detailsRepository;
        this.purchaseMapper = purchaseMapper;
        this.detailsMapper = detailsMapper;
    }

    @Override
    public Long execute() {
        Purchase purchase = purchaseMapper.transform(toSaveOrUpdate);

        /*
         * Do eventual work on domain object.
         * Here to conversion only ensure null safe before saving.
         */

        // Extract details and remove from purchase
        Details details = purchase.getPurchaseDetails();
        purchase.setPurchaseDetails(Details.NULL);

        // Save purchase
        PurchaseDTO toSaveDto = purchaseMapper.transform(purchase);
        long id = purchaseRepository.saveOrUpdate(toSaveDto);

        // Update id on details
        details.setId(id);

        detailsRepository.saveOrUpdate(detailsMapper.transform(details));
        return id;
    }
}
