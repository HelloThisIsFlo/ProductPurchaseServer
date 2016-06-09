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

    @Before
    public void setUp() throws Exception {
        fakePurchaseDetailsRepo = new FakePurchaseDetailsRepository();
    }

    @Test
    public void getAll_returnListWithMoreThan2Elements() throws Exception {
        List<DetailsDTO> result = fakePurchaseDetailsRepo.getAllFromPurchaseId(EMPTY_LIST_ID);
        assertTrue("List should have more than 2 elements", result.size() > 2);
    }

    @Test
    public void getAll_allElementsNonNull() throws Exception {
        List<DetailsDTO> result = fakePurchaseDetailsRepo.getAllFromPurchaseId(EMPTY_LIST_ID);
        for (DetailsDTO detailsDTO : result) {
            assertNotNull(detailsDTO);
        }
    }

}