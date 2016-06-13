package com.professionalbeginner.data.purchase;

import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.driven.PurchaseRepository;

import java.util.ArrayList;
import java.util.List;

public class MockPurchaseRepository implements PurchaseRepository {

    List<PurchaseDTO> dataset;

    public MockPurchaseRepository() {
        dataset = new ArrayList<>();
    }

    @Override
    public List<PurchaseDTO> getAll() {
        return dataset;
    }

    @Override
    public Long saveOrUpdate(PurchaseDTO toSave) {
        if (toSave == null) {
            return -1L;
        }
        return saveNonNull(toSave);
    }

    private long saveNonNull(PurchaseDTO toSave) {

        int id = generateId(toSave);
        toSave = updateWithId(toSave, id);

        dataset.add(getIndexFromId(id), toSave);
        return id;
    }

    private int getIndexFromId(int id) {
        return id - 1;
    }

    private PurchaseDTO updateWithId(PurchaseDTO toSave, long id) {
        return new PurchaseDTO(
                id,
                toSave.productType,
                toSave.expires,
                toSave.purchaseDetails
        );
    }

    private int generateId(PurchaseDTO toSave) {
        if (toSave.id <= 0) {
            return dataset.size() + 1;
        }
        return (int) toSave.id;
    }
}
