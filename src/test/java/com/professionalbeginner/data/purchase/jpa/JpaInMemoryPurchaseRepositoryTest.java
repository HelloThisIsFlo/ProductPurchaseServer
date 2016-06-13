package com.professionalbeginner.data.purchase.jpa;

import com.professionalbeginner.data.purchase.MockPurchaseRepository;
import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.driven.PurchaseRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by Florian on 13/06/16.
 */
public class JpaInMemoryPurchaseRepositoryTest {

    @Mock
    JpaPurchaseRepository jpaRepo;
    
    PurchaseRepository repository;

    private PurchaseDTO purchaseWithNoId1;
    private PurchaseDTO purchaseWithNoId2;
    private PurchaseDTO purchase1;
    private PurchaseDTO purchase2;
    private PurchaseDTO purchase3;
    private PurchaseDTO purchase4;

    @Captor
    ArgumentCaptor<PurchaseJPA> purchaseJPAArgumentCaptor;

    JpaPurchaseMapper mapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mapper = new JpaPurchaseMapper();

        purchaseWithNoId1 = new PurchaseDTO(0, "type with no id 1", LocalDateTime.MIN, DetailsDTO.NULL);
        purchaseWithNoId2 = new PurchaseDTO(0, "type with no id 2", LocalDateTime.MIN, DetailsDTO.NULL);
        purchase1 = new PurchaseDTO(1, "type 1", LocalDateTime.MIN, DetailsDTO.NULL);
        purchase2 = new PurchaseDTO(2, "type 2", LocalDateTime.MIN, DetailsDTO.NULL);
        purchase3 = new PurchaseDTO(3, "type 3", LocalDateTime.MIN, DetailsDTO.NULL);
        purchase4 = new PurchaseDTO(4, "type 4", LocalDateTime.MIN, DetailsDTO.NULL);

        repository = new JpaInMemoryPurchaseRepository(jpaRepo, new JpaPurchaseMapper());
    }

    @Test
    public void saveNull_returnMinus1() throws Exception {
        assertEquals(new Long(-1), repository.saveOrUpdate(null));
    }

    @Test
    public void saveNotNull_returnId() throws Exception {
        long id = repository.saveOrUpdate(purchase1);

        verify(jpaRepo).save(purchaseJPAArgumentCaptor.capture());

        PurchaseJPA saved = purchaseJPAArgumentCaptor.getValue();
        PurchaseDTO savedAsDto = mapper.transform(saved);

        assertEquals(purchase1, savedAsDto);
        assertTrue("Should return valid id but returned: " + id, id > 0);
    }

    @Test
    public void purchaseWithId0OrLess_attributeNewId() throws Exception {
        long id = repository.saveOrUpdate(purchaseWithNoId1);

        verify(jpaRepo).save(purchaseJPAArgumentCaptor.capture());

        PurchaseJPA saved = purchaseJPAArgumentCaptor.getValue();
        PurchaseDTO savedAsDto = mapper.transform(saved);

        PurchaseDTO expected = new PurchaseDTO(id, purchaseWithNoId1.productType,
                purchaseWithNoId1.expires, purchaseWithNoId1.purchaseDetails);
        assertEquals(expected, savedAsDto);
        assertTrue("Should return valid id but returned: " + id, id > 0);
    }
}