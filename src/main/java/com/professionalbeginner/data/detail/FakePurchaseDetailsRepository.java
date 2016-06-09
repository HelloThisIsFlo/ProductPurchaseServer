package com.professionalbeginner.data.detail;

import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.driven.PurchaseDetailsRepository;

import java.util.ArrayList;
import java.util.List;

public class FakePurchaseDetailsRepository implements PurchaseDetailsRepository {


    private List<DetailsDTO> fakeDataset;

    public FakePurchaseDetailsRepository(List<DetailsDTO> fakeDataset) {
        this.fakeDataset = fakeDataset;
    }

    @Override
    public List<DetailsDTO> getAllFromPurchaseId(List<Long> purchaseIds) {
        return fakeDataset;
    }
}
