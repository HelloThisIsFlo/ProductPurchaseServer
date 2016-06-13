package com.professionalbeginner.domain.application.driving;

import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.DetailsMapper;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.PurchaseMapper;
import com.professionalbeginner.domain.application.driven.PurchaseDetailsRepository;
import com.professionalbeginner.domain.application.driven.PurchaseRepository;
import com.professionalbeginner.domain.core.DetailsFactory;
import com.professionalbeginner.domain.core.PurchaseFactory;
import com.professionalbeginner.domain.core.validator.ValidateAllValidator;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SaveOrUpdateUseCaseTest {

    SaveOrUpdateUseCaseFactory useCaseFactory;

    @Mock
    PurchaseRepository purchaseRepository;
    @Mock
    PurchaseDetailsRepository detailsRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        DetailsFactory detailsFactory = new DetailsFactory();
        PurchaseFactory purchaseFactory = new PurchaseFactory(new ValidateAllValidator());

        DetailsMapper detailsMapper = new DetailsMapper(detailsFactory);
        PurchaseMapper purchaseMapper = new PurchaseMapper(purchaseFactory, detailsMapper);

        useCaseFactory = new SaveOrUpdateUseCaseFactory(
                purchaseRepository,
                detailsRepository, purchaseMapper, detailsMapper);
    }

    @Test
    public void saveWithNoProductDetails_SaveOnPurchaseRepository() throws Exception {
        long expectedId = 1;
        PurchaseDTO withoutDetails = new PurchaseDTO(expectedId, "type", LocalDateTime.MIN, DetailsDTO.NULL);
        SaveOrUpdateUseCase useCase = useCaseFactory.make(withoutDetails);
        when(purchaseRepository.saveOrUpdate(any())).thenReturn(expectedId);


        long fromUseCaseId = useCase.execute();

        verify(purchaseRepository).saveOrUpdate(withoutDetails);
        assertEquals(fromUseCaseId, expectedId);
    }

    @Test
    public void beforeSaving_convertNullsToEmptyTypes() throws Exception {
        long expectedId = 1;
        PurchaseDTO withNulls = new PurchaseDTO(expectedId, null, LocalDateTime.MIN, null);
        SaveOrUpdateUseCase useCase = useCaseFactory.make(withNulls);
        when(purchaseRepository.saveOrUpdate(any())).thenReturn(expectedId);

        long fromUseCaseId = useCase.execute();

        PurchaseDTO expectedToBeSaved_withEmtpyInsteadOfNull =
                new PurchaseDTO(expectedId, "", LocalDateTime.MIN, DetailsDTO.NULL);
        verify(purchaseRepository).saveOrUpdate(expectedToBeSaved_withEmtpyInsteadOfNull);
        assertEquals(fromUseCaseId, expectedId);
    }

    @Test
    public void saveWithProductDetails_SeparateSave_PurchaseWithoutDetails_Details() throws Exception {
        long expectedId = 1;

        DetailsDTO details = new DetailsDTO(expectedId, "description", 1, 38.2);
        PurchaseDTO withDetails = new PurchaseDTO(expectedId, "type", LocalDateTime.MIN, details);
        PurchaseDTO withoutDetails = new PurchaseDTO(expectedId, "type", LocalDateTime.MIN, DetailsDTO.NULL);

        SaveOrUpdateUseCase useCase = useCaseFactory.make(withDetails);
        when(purchaseRepository.saveOrUpdate(any())).thenReturn(expectedId);

        long fromUseCaseId = useCase.execute();

        // Save the Purchase without the Details
        verify(purchaseRepository).saveOrUpdate(withoutDetails);
        // Save the Details separately
        verify(detailsRepository).saveOrUpdate(details);
        assertEquals(fromUseCaseId, expectedId);
    }

    @Test
    @Ignore
    public void ProductDetailHasDifferentId_OverrideWithPurchaseId() throws Exception {
        long expectedId = 1;
        long differentId = 2;

        DetailsDTO details = new DetailsDTO(differentId, "description", 1, 38.2);
        DetailsDTO detailsWithCorrectId = new DetailsDTO(expectedId, "description", 1, 38.2);
        PurchaseDTO withDetails = new PurchaseDTO(expectedId, "type", LocalDateTime.MIN, details);
        PurchaseDTO withoutDetails = new PurchaseDTO(expectedId, "type", LocalDateTime.MIN, DetailsDTO.NULL);

        SaveOrUpdateUseCase useCase = useCaseFactory.make(withDetails);
        when(purchaseRepository.saveOrUpdate(any())).thenReturn(expectedId);

        long fromUseCaseId = useCase.execute();

        // Save the Purchase without the Details
        verify(purchaseRepository).saveOrUpdate(withoutDetails);
        // Save the Details separately
        verify(detailsRepository).saveOrUpdate(detailsWithCorrectId);
        assertEquals(fromUseCaseId, expectedId);

    }

    /*
    todo
     - No Purchase Id --------> Detail id = resulting purchase id (after save)
     */
}