package com.professionalbeginner.data.detail;

import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.driven.DetailsRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is a test for the FakeDetailsRepository TestDouble.
 * The fake repository arbitrarily returns a list with more than 2 non null elements.
 */
public class FakeDetailsRepositoryTest {

    private static final List<Long> EMPTY_LIST_ID = new ArrayList<>();
    private DetailsRepository fakePurchaseDetailsRepo;
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

        fakePurchaseDetailsRepo = new FakeDetailsRepository(fakeDataset);
    }


    @Test
    public void allIds_returnAllDetails() throws Exception {
        List<Long> purchaseIds = makeIdList(1, 2, 3);

        assertEquals(fakeDataset, fakePurchaseDetailsRepo.getAllFromPurchaseId(purchaseIds));
    }

    @Test
    public void first2Ids_returnFirst2Details() throws Exception {
        List<Long> purchaseIds = makeIdList(1, 2);

        assertEquals(fakeDataset.subList(0, 2), fakePurchaseDetailsRepo.getAllFromPurchaseId(purchaseIds));
    }

    @Test
    public void nonExistingId_returnNullObject() throws Exception {
        List<Long> purchaseIds = makeIdList(23);

        List<DetailsDTO> expectedResult = new ArrayList<>();
        expectedResult.add(DetailsDTO.NULL);

        assertEquals(expectedResult, fakePurchaseDetailsRepo.getAllFromPurchaseId(purchaseIds));
    }

    @Test
    public void keepOrder() throws Exception {
        List<Long> purchaseIds = makeIdList(1,3,2);

        List<DetailsDTO> result = fakePurchaseDetailsRepo.getAllFromPurchaseId(purchaseIds);


        assertEquals(3, result.size());

        // Check order
        assertEquals(fakeDataset.get(0), result.get(0));
        assertEquals(fakeDataset.get(1), result.get(2));
        assertEquals(fakeDataset.get(2), result.get(1));
    }

    private List<Long> makeIdList(int... ids) {
        List<Long> result = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            result.add((long) ids[i]);
        }
        return result;
    }
}