package com.professionalbeginner.data.purchase;

import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.driven.PurchaseRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * This is a test for the FakePurchaseRepository TestDouble.
 * The fake repository arbitrarily returns a list with more than 2 non null elements, with empty details
 */
public class FakePurchaseRepositoryTest {


    private PurchaseRepository fakePurchaseRepo;

    @Before
    public void setUp() throws Exception {
        fakePurchaseRepo = new FakePurchaseRepository();
    }

    @Test
    public void getAll_returnListWithMoreThan2Elements() throws Exception {
        List<PurchaseDTO> result = fakePurchaseRepo.getAll();
        assertTrue("List should have more than 2 elements", result.size() > 2);
    }

    @Test
    public void getAll_allElementsNonNull() throws Exception {
        List<PurchaseDTO> result = fakePurchaseRepo.getAll();
        for (PurchaseDTO purchaseDTO : result) {
            assertNotNull(purchaseDTO);
        }
    }

    @Test
    public void getAll_allPurchaseDetailEmpty() throws Exception {
        List<PurchaseDTO> result = fakePurchaseRepo.getAll();
        for (PurchaseDTO purchaseDTO : result) {
            assertEquals(DetailsDTO.NULL, purchaseDTO.purchaseDetails);
        }
    }
}