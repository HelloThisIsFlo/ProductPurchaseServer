package com.professionalbeginner.data.detail;

import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.driven.DetailsRepository;

import java.util.ArrayList;
import java.util.List;

public class FakeDetailsRepository implements DetailsRepository {


    private List<DetailsDTO> fakeDataset;

    public FakeDetailsRepository(List<DetailsDTO> fakeDataset) {
        this.fakeDataset = fakeDataset;
    }

    @Override
    public List<DetailsDTO> getAllFromPurchaseId(List<Long> purchaseIds) {
        List<DetailsDTO> result = new ArrayList<>();

        for (Long purchaseId : purchaseIds) {
            DetailsDTO correspondingDetail = getFromId(purchaseId);
            result.add(correspondingDetail);
        }

        return result;
    }

    private DetailsDTO getFromId(Long purchaseId) {
        for (DetailsDTO detailsDTO : fakeDataset) {
            if (purchaseId.equals(detailsDTO.id)) {
                return detailsDTO;
            }
        }
        return DetailsDTO.NULL;
    }

    @Override
    public void saveOrUpdate(DetailsDTO toSave) {
        throw new RuntimeException("Not implemented, use mock implementation instead");
    }
}
