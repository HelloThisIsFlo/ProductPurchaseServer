package com.professionalbeginner.data.detail;

import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.driven.PurchaseDetailsRepository;

import java.util.ArrayList;
import java.util.List;

public class FakePurchaseDetailsRepository implements PurchaseDetailsRepository {


    private List<DetailsDTO> fakeDataset;

    public FakePurchaseDetailsRepository() {
        fakeDataset = new ArrayList<>();

        DetailsDTO detailsDTO1 = new DetailsDTO(1, "description 1", 1, 1);
        DetailsDTO detailsDTO2 = new DetailsDTO(2, "description 2", 2, 2);
        DetailsDTO detailsDTO3 = new DetailsDTO(3, "description 3", 3, 3);

        fakeDataset.add(detailsDTO1);
        fakeDataset.add(detailsDTO2);
        fakeDataset.add(detailsDTO3);
    }

    @Override
    public List<DetailsDTO> getAllFromPurchaseId(List<Long> purchaseIds) {
        return fakeDataset;
    }
}
