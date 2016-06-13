package com.professionalbeginner.domain.application;

import com.professionalbeginner.domain.core.Details;
import com.professionalbeginner.domain.core.DetailsFactory;
import com.professionalbeginner.domain.core.Purchase;
import com.professionalbeginner.domain.core.PurchaseFactory;
import com.professionalbeginner.domain.core.validator.ValidateAllValidator;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * Created by Florian on 13/06/16.
 */
public class PurchaseMapperTest {

    private DetailsFactory detailsFactory;
    private DetailsMapper detailsMapper;
    private PurchaseFactory purchaseFactory;
    private PurchaseMapper mapper;

    private Details details;
    private DetailsDTO detailsDTO;

    @Before
    public void setUp() throws Exception {
        detailsFactory = new DetailsFactory();
        detailsMapper = new DetailsMapper(detailsFactory);
        purchaseFactory = new PurchaseFactory(new ValidateAllValidator());
        mapper = new PurchaseMapper(purchaseFactory, detailsMapper);

        details = detailsFactory.make(1, "hello", 2, 3.4);
        detailsDTO = new DetailsDTO(1, "hello", 2, 3.4);
    }

    @Test
    public void transformDtoToDomain() throws Exception {
        PurchaseDTO dto = new PurchaseDTO(1, "asdf", LocalDateTime.MIN, detailsDTO);
        Purchase domain = mapper.transform(dto);

        assertEquals(dto.id, domain.getId());
        assertEquals(dto.productType, domain.getProductType());
        assertEquals(dto.expires, domain.getExpires());
        assertEquals(details, domain.getPurchaseDetails());
    }

    @Test
    public void transformDomainToDto() throws Exception {
        Purchase domain = purchaseFactory.make(1, "asdf", LocalDateTime.MIN, details);
        PurchaseDTO dto = mapper.transform(domain);

        assertEquals(domain.getId(), dto.id);
        assertEquals(domain.getProductType(), dto.productType);
        assertEquals(domain.getExpires(), dto.expires);
        assertEquals(detailsDTO, dto.purchaseDetails);
    }

    @Test
    public void nullType_replaceWithEmpty() throws Exception {
        PurchaseDTO withNullType = new PurchaseDTO(1, null, LocalDateTime.MIN, detailsDTO);

        Purchase result = mapper.transform(withNullType);
        assertEquals("", result.getProductType());
    }

    @Test
    public void nullExpire_replaceWithMIN() throws Exception {
        PurchaseDTO withNullDate = new PurchaseDTO(1, "type", null, detailsDTO);

        Purchase result = mapper.transform(withNullDate);
        assertEquals(LocalDateTime.MIN, result.getExpires());
    }

    @Test
    public void nullDetails_replaceWithNullObject() throws Exception {
        PurchaseDTO withNullDetails = new PurchaseDTO(1, "type", LocalDateTime.MIN, null);

        Purchase result = mapper.transform(withNullDetails);
        assertEquals(Details.NULL, result.getPurchaseDetails());
    }

    @Test
    public void detailsHasDifferentId_OverrideWithPurchaseId() throws Exception {
        long expectedId = 1;
        long differentId = 2;

        DetailsDTO withDifferentId = new DetailsDTO(differentId, "asdfsdfsfds", 1, 1);
        PurchaseDTO differentIdOnDetail = new PurchaseDTO(expectedId, "asd", LocalDateTime.MIN, withDifferentId);

        Purchase result = mapper.transform(differentIdOnDetail);
        Details resultingDetails = result.getPurchaseDetails();

        assertEquals(expectedId, resultingDetails.getId());
    }
}