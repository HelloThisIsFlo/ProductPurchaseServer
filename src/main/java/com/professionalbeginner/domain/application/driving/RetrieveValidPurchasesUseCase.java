package com.professionalbeginner.domain.application.driving;

import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.DetailsMapper;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.PurchaseMapper;
import com.professionalbeginner.domain.application.driven.DetailsRepository;
import com.professionalbeginner.domain.application.driven.PurchaseRepository;
import com.professionalbeginner.domain.application.driven.PurchaseSerializer;
import com.professionalbeginner.domain.core.Details;
import com.professionalbeginner.domain.core.Purchase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class RetrieveValidPurchasesUseCase implements UseCase<String> {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;
    private final DetailsRepository detailsRepository;
    private final DetailsMapper detailsMapper;
    private final PurchaseSerializer serializer;

    private final LocalDateTime currentTime;

    RetrieveValidPurchasesUseCase(PurchaseRepository purchaseRepository,
                                  PurchaseMapper purchaseMapper,
                                  DetailsRepository detailsRepository,
                                  DetailsMapper detailsMapper,
                                  PurchaseSerializer serializer, LocalDateTime currentTime) {
        this.purchaseRepository = checkNotNull(purchaseRepository);
        this.purchaseMapper = checkNotNull(purchaseMapper);
        this.detailsRepository = checkNotNull(detailsRepository);
        this.detailsMapper = checkNotNull(detailsMapper);
        this.serializer = checkNotNull(serializer);
        this.currentTime = checkNotNull(currentTime);
    }

    @Override
    public String execute() {
        List<Purchase> allPurchase = getAllPurchases();
        List<Purchase> validPurchases = getValidPurchases(allPurchase);
        List<Long> validPurchaseIds = extractIds(validPurchases);
        List<Details> validDetails = getDetailsFromId(validPurchaseIds);

        List<Purchase> result = combinePurchaseWithCorrespondingDetails(validPurchases, validDetails);
        String resultJson = serializePurchases(result);

        return resultJson;
    }

    private String serializePurchases(List<Purchase> toSerialize) {
        List<PurchaseDTO> resultDTO = new ArrayList<>(toSerialize.size());
        for (Purchase purchase : toSerialize) {
            resultDTO.add(purchaseMapper.transform(purchase));
        }

        return serializer.serializeAll(resultDTO);
    }

    private List<Purchase> combinePurchaseWithCorrespondingDetails(List<Purchase> purchases, List<Details> details) {
        assert purchases.size() == details.size();
        for (int i = 0; i < purchases.size(); i++) {
            Purchase purchase = purchases.get(i);
            purchase.setPurchaseDetails(details.get(i));
        }
        return purchases;
    }

    private List<Details> getDetailsFromId(List<Long> purchaseIds) {
        List<DetailsDTO> validDetailsDTO = detailsRepository.getAllFromPurchaseId(purchaseIds);
        List<Details> validDetails = new ArrayList<>(validDetailsDTO.size());
        for (DetailsDTO detailsDTO : validDetailsDTO) {
            validDetails.add(detailsMapper.transform(detailsDTO));
        }
        return validDetails;
    }

    private List<Purchase> getValidPurchases(List<Purchase> allPurchase) {
        List<Purchase> validPurchases = new ArrayList<>();
        for (Purchase purchase : allPurchase) {
            if (purchase.validate(currentTime))
                validPurchases.add(purchase);
        }
        return validPurchases;
    }

    private List<Purchase> getAllPurchases() {
        List<PurchaseDTO> allPurchaseDTOs = purchaseRepository.getAll();
        List<Purchase> allPurchase = new ArrayList<>(allPurchaseDTOs.size());
        for (PurchaseDTO purchaseDTO : allPurchaseDTOs) {
            allPurchase.add(purchaseMapper.transform(purchaseDTO));
        }
        return allPurchase;
    }

    private List<Long> extractIds(List<Purchase> purchases) {
        List<Long> ids = new ArrayList<>(purchases.size());
        for (Purchase purchase : purchases) {
            ids.add(purchase.getId());
        }
        return ids;
    }
}
