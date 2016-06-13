package com.professionalbeginner.data.purchase;

import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.driven.PurchaseRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FakePurchaseRepository implements PurchaseRepository {

    List<PurchaseDTO> fakeDataset;

    public FakePurchaseRepository(List<PurchaseDTO> fakeDataset) {
        this.fakeDataset = fakeDataset;
    }

    @Override
    public List<PurchaseDTO> getAll() {
        return fakeDataset;
    }

    @Override
    public Long saveOrUpdate(PurchaseDTO toSave) {
        throw new RuntimeException("Not implemented");
    }
}
