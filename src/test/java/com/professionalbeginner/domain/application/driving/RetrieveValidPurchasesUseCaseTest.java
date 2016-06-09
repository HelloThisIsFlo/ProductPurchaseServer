package com.professionalbeginner.domain.application.driving;

import com.professionalbeginner.data.detail.FakePurchaseDetailsRepository;
import com.professionalbeginner.data.purchase.FakePurchaseRepository;
import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.DetailsMapper;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.PurchaseMapper;
import com.professionalbeginner.domain.application.driven.PurchaseDetailsRepository;
import com.professionalbeginner.domain.application.driven.PurchaseRepository;
import com.professionalbeginner.domain.application.driven.PurchaseSerializer;
import com.professionalbeginner.domain.core.Details;
import com.professionalbeginner.domain.core.DetailsFactory;
import com.professionalbeginner.domain.core.Purchase;
import com.professionalbeginner.domain.core.PurchaseFactory;
import com.professionalbeginner.domain.core.validator.PurchaseValidator;
import com.professionalbeginner.domain.core.validator.ValidateAllValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Florian on 09/06/16.
 */
public class RetrieveValidPurchasesUseCaseTest {

    private final static String SERIALIZED_RESULT = "{HELLO}";

    RetrieveValidPurchasesUseCase useCase;

    PurchaseRepository purchaseRepository;
    PurchaseDetailsRepository detailsRepository;
    PurchaseMapper purchaseMapper;
    DetailsMapper detailsMapper;
    PurchaseValidator validator;

    List<DetailsDTO> fakeDetailsDataset;
    List<PurchaseDTO> fakePurchaseDataset;
    List<PurchaseDTO> fakePurchaseWithDetailsDataset;

    @Mock
    PurchaseSerializer serializer;
    @Mock
    UseCase.OnSuccessCallback<String> onSuccessCallback;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        fakeDetailsDataset = new ArrayList<>(3);
        fakePurchaseDataset = new ArrayList<>(3);
        fakePurchaseWithDetailsDataset = new ArrayList<>(3);

        DetailsDTO detailsDTO1 = new DetailsDTO(1, "one", 1, 1);
        DetailsDTO detailsDTO2 = new DetailsDTO(2, "two", 2, 2);
        DetailsDTO detailsDTO3 = new DetailsDTO(3, "three", 3, 3);

        LocalDateTime dateTime1 = LocalDateTime.of(2016, 1, 1, 1, 1);
        LocalDateTime dateTime2 = LocalDateTime.of(2016, 1, 1, 1, 2);
        LocalDateTime dateTime3 = LocalDateTime.of(2016, 1, 1, 1, 3);

        PurchaseDTO purchaseDTO1 = new PurchaseDTO(1, "type 1", dateTime1, DetailsDTO.NULL);
        PurchaseDTO purchaseDTO2 = new PurchaseDTO(2, "type 2", dateTime2, DetailsDTO.NULL);
        PurchaseDTO purchaseDTO3 = new PurchaseDTO(3, "type 3", dateTime3, DetailsDTO.NULL);


        fakeDetailsDataset.add(detailsDTO1);
        fakeDetailsDataset.add(detailsDTO2);
        fakeDetailsDataset.add(detailsDTO3);

        fakePurchaseDataset.add(purchaseDTO1);
        fakePurchaseDataset.add(purchaseDTO2);
        fakePurchaseDataset.add(purchaseDTO3);

        PurchaseDTO purchaseDTOWithDetails1 = new PurchaseDTO(1, "type 1", dateTime1, detailsDTO1);
        PurchaseDTO purchaseDTOWithDetails2 = new PurchaseDTO(2, "type 2", dateTime2, detailsDTO2);
        PurchaseDTO purchaseDTOWithDetails3 = new PurchaseDTO(3, "type 3", dateTime3, detailsDTO3);

        fakePurchaseWithDetailsDataset.add(purchaseDTOWithDetails1);
        fakePurchaseWithDetailsDataset.add(purchaseDTOWithDetails2);
        fakePurchaseWithDetailsDataset.add(purchaseDTOWithDetails3);
    }

    private void initUseCase(PurchaseValidator validator) {
        DetailsFactory detailsFactory = new DetailsFactory();
        PurchaseFactory purchaseFactory = new PurchaseFactory(validator);

        purchaseRepository = new FakePurchaseRepository(fakePurchaseDataset);
        detailsMapper = new DetailsMapper(detailsFactory);
        purchaseMapper = new PurchaseMapper(purchaseFactory, detailsMapper);
        detailsRepository = new FakePurchaseDetailsRepository(fakeDetailsDataset);

        useCase = new RetrieveValidPurchasesUseCase(
                purchaseRepository,
                purchaseMapper,
                detailsRepository,
                detailsMapper,
                validator,
                serializer
        );
    }

    @Test
    public void validateAll_serializeAll() throws Exception {
        initUseCase(new ValidateAllValidator());
        when(serializer.serializeAll(anyListOf(PurchaseDTO.class))).thenReturn(SERIALIZED_RESULT);

        useCase.execute(onSuccessCallback);
        verify(serializer).serializeAll(eq(fakePurchaseWithDetailsDataset));
        verify(onSuccessCallback).onSuccess(SERIALIZED_RESULT);
    }
}