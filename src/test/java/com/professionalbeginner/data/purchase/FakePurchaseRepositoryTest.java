package com.professionalbeginner.data.purchase;

import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.driven.PurchaseRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * This is a test for the FakePurchaseRepository TestDouble.
 * The fake repository arbitrarily returns a list with more than 2 non null elements, with empty details
 */
public class FakePurchaseRepositoryTest {


    private PurchaseRepository fakePurchaseRepo;
    List<PurchaseDTO> fakeDataset;


    @Before
    public void setUp() throws Exception {
        fakeDataset = new ArrayList<>();

        LocalDateTime dateTime1 = LocalDateTime.of(2016, 1, 1, 1, 1);
        LocalDateTime dateTime2 = LocalDateTime.of(2016, 1, 1, 1, 2);
        LocalDateTime dateTime3 = LocalDateTime.of(2016, 1, 1, 1, 3);

        PurchaseDTO purchaseDTO1 = new PurchaseDTO(1, "type 1", dateTime1, DetailsDTO.NULL);
        PurchaseDTO purchaseDTO2 = new PurchaseDTO(2, "type 2", dateTime2, DetailsDTO.NULL);
        PurchaseDTO purchaseDTO3 = new PurchaseDTO(3, "type 3", dateTime3, DetailsDTO.NULL);

        fakeDataset.add(purchaseDTO1);
        fakeDataset.add(purchaseDTO2);
        fakeDataset.add(purchaseDTO3);

        fakePurchaseRepo = new FakePurchaseRepository(fakeDataset);
    }

    @Test
    public void getAll_returnFakeDataset() throws Exception {
        assertEquals(fakeDataset, fakePurchaseRepo.getAll());
    }
}