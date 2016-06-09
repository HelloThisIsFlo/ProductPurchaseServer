package com.professionalbeginner.data.purchase;

import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.driven.PurchaseRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FakePurchaseRepository implements PurchaseRepository {


    List<PurchaseDTO> fakeDataset;

    public FakePurchaseRepository() {
        this.fakeDataset = new ArrayList<>(3);
        initDataset();
    }

    private void initDataset() {
        DetailsDTO detailsDTO1 = new DetailsDTO(1, "description 1", 1, 1);
        DetailsDTO detailsDTO2 = new DetailsDTO(2, "description 2", 2, 2);
        DetailsDTO detailsDTO3 = new DetailsDTO(3, "description 3", 3, 3);

        LocalDateTime dateTime1 = LocalDateTime.of(2016, 1, 1, 1, 1);
        LocalDateTime dateTime2 = LocalDateTime.of(2016, 1, 1, 1, 2);
        LocalDateTime dateTime3 = LocalDateTime.of(2016, 1, 1, 1, 3);

        PurchaseDTO purchaseDTO1 = new PurchaseDTO(1, "type 1", dateTime1, detailsDTO1);
        PurchaseDTO purchaseDTO2 = new PurchaseDTO(2, "type 2", dateTime2, detailsDTO2);
        PurchaseDTO purchaseDTO3 = new PurchaseDTO(3, "type 3", dateTime3, detailsDTO3);

        fakeDataset.add(purchaseDTO1);
        fakeDataset.add(purchaseDTO2);
        fakeDataset.add(purchaseDTO3);
    }

    @Override
    public List<PurchaseDTO> getAll() {
        return fakeDataset;
    }
}
