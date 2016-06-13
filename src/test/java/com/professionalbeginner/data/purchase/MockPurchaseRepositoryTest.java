package com.professionalbeginner.data.purchase;

import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.driven.PurchaseRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MockPurchaseRepositoryTest {

    private PurchaseRepository repository;

    private PurchaseDTO purchaseWithNoId1;
    private PurchaseDTO purchaseWithNoId2;
    private PurchaseDTO purchase1;
    private PurchaseDTO purchase2;
    private PurchaseDTO purchase3;
    private PurchaseDTO purchase4;

    @Before
    public void setUp() throws Exception {
        purchaseWithNoId1 = new PurchaseDTO(0, "type with no id 1", LocalDateTime.MIN, DetailsDTO.NULL);
        purchaseWithNoId2 = new PurchaseDTO(0, "type with no id 2", LocalDateTime.MIN, DetailsDTO.NULL);
        purchase1 = new PurchaseDTO(1, "type 1", LocalDateTime.MIN, DetailsDTO.NULL);
        purchase2 = new PurchaseDTO(2, "type 2", LocalDateTime.MIN, DetailsDTO.NULL);
        purchase3 = new PurchaseDTO(3, "type 3", LocalDateTime.MIN, DetailsDTO.NULL);
        purchase4 = new PurchaseDTO(4, "type 4", LocalDateTime.MIN, DetailsDTO.NULL);

        repository = new MockPurchaseRepository();
    }

    @Test
    public void saveNull_returnMinus1() throws Exception {
        assertEquals(new Long(-1), repository.saveOrUpdate(null));
    }

    @Test
    public void saveNotNull_returnId() throws Exception {
        long id = repository.saveOrUpdate(purchase1);
        assertTrue("Should return valid id but returned: " + id, id > 0);
    }

    @Test
    public void purchaseWithId0OrLess_attributeNewId() throws Exception {
        long id = repository.saveOrUpdate(purchaseWithNoId1);
        assertTrue("Should return valid id but returned: " + id, id > 0);
    }

    @Test
    public void savePurchase_getAllReturnPurchase_WithId() throws Exception {
        long id = repository.saveOrUpdate(purchase1);
        List<PurchaseDTO> fromRepo = repository.getAll();

        assertEquals(1, fromRepo.size());

        PurchaseDTO purchaseFromRepo = fromRepo.get(0);
        purchase1 = updateWithId(purchase1, id);
        assertEquals(purchase1, purchaseFromRepo);
    }

    @Test
    public void saveMultiplePurchase_getAllReturnPurchase_WithId() throws Exception {
        long id1 = repository.saveOrUpdate(purchase1);
        long id2 = repository.saveOrUpdate(purchase2);
        long id3 = repository.saveOrUpdate(purchase3);

        List<PurchaseDTO> expectedList = new ArrayList<>();
        purchase1 = updateWithId(purchase1, id1);
        purchase2 = updateWithId(purchase2, id2);
        purchase3 = updateWithId(purchase3, id3);
        expectedList.add(purchase1);
        expectedList.add(purchase2);
        expectedList.add(purchase3);

        List<PurchaseDTO> fromRepo = repository.getAll();

        assertEquals(expectedList, fromRepo);
    }

    @Test
    public void saveMultiplePurchaseWithNoId_getAllReturnPurchase_WithId() throws Exception {
        long id1 = repository.saveOrUpdate(purchase1);
        long id2 = repository.saveOrUpdate(purchaseWithNoId1);
        long id3 = repository.saveOrUpdate(purchaseWithNoId2);
        long id4 = repository.saveOrUpdate(purchase4);

        List<PurchaseDTO> expectedList = new ArrayList<>();
        purchase1 = updateWithId(purchase1, id1);
        purchaseWithNoId1 = updateWithId(purchaseWithNoId1, id2);
        purchaseWithNoId2 = updateWithId(purchaseWithNoId2, id3);
        purchase4 = updateWithId(purchase4, id4);
        expectedList.add(purchase1);
        expectedList.add(purchaseWithNoId1);
        expectedList.add(purchaseWithNoId2);
        expectedList.add(purchase4);

        List<PurchaseDTO> fromRepo = repository.getAll();

        assertEquals(4, fromRepo.size());
        assertEquals(expectedList, fromRepo);
    }

    @Test
    public void updateExistingPurchase() throws Exception {
        long id1 = repository.saveOrUpdate(purchase1);
        repository.saveOrUpdate(purchase1);

        assertEquals(purchase1, repository.getAll().get(0));

        // Update
        purchase2 = updateWithId(purchase2, id1); //set existing id on purchase 2
        repository.saveOrUpdate(purchase2); // Save purchase2 in lieu of existing purchase1

        assertEquals(purchase2, repository.getAll().get(0));
    }

    private PurchaseDTO updateWithId(PurchaseDTO toUpdate, long id) {
        return new PurchaseDTO(
                id,
                toUpdate.productType,
                toUpdate.expires,
                toUpdate.purchaseDetails
        );
    }
}