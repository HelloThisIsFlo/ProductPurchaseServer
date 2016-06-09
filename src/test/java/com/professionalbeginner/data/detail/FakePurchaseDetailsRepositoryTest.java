package com.professionalbeginner.data.detail;

import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.driven.PurchaseDetailsRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * This is a test for the FakePurchaseDetailsRepository TestDouble.
 * The fake repository arbitrarily returns a list with more than 2 non null elements.
 */
public class FakePurchaseDetailsRepositoryTest {

    private PurchaseDetailsRepository fakePurchaseDetailsRepo;
    private static final List<Long> EMPTY_LIST_ID = new ArrayList<>();
    private List<DetailsDTO> fakeDataset;

    @Before
    public void setUp() throws Exception {
        fakeDataset = new ArrayList<>();

        DetailsDTO detailsDTO1 = new DetailsDTO(1, "description 1", 1, 1);
        DetailsDTO detailsDTO2 = new DetailsDTO(2, "description 2", 2, 2);
        DetailsDTO detailsDTO3 = new DetailsDTO(3, "description 3", 3, 3);

        fakeDataset.add(detailsDTO1);
        fakeDataset.add(detailsDTO2);
        fakeDataset.add(detailsDTO3);

        fakePurchaseDetailsRepo = new FakePurchaseDetailsRepository(fakeDataset);
    }


    @Test
    public void getAll_returnFakeDataset() throws Exception {
        assertEquals(fakeDataset, fakePurchaseDetailsRepo.getAllFromPurchaseId(EMPTY_LIST_ID));
    }

}