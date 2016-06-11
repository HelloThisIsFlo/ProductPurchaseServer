package com.professionalbeginner.domain.application.driving;

import com.professionalbeginner.data.purchase.FakePurchaseRepository;
import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.DetailsMapper;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.PurchaseMapper;
import com.professionalbeginner.domain.application.driven.PurchaseDetailsRepository;
import com.professionalbeginner.domain.application.driven.PurchaseRepository;
import com.professionalbeginner.domain.application.driven.PurchaseSerializer;
import com.professionalbeginner.domain.core.DetailsFactory;
import com.professionalbeginner.domain.core.PurchaseFactory;
import com.professionalbeginner.domain.core.executor.ExecutorServicesUseCaseExecutor;
import com.professionalbeginner.domain.core.executor.FakeMainThreadUseCaseExecutor;
import com.professionalbeginner.domain.core.executor.UseCaseExecutor;
import com.professionalbeginner.domain.core.validator.PurchaseValidator;
import com.professionalbeginner.domain.core.validator.ValidateAllValidator;
import com.professionalbeginner.domain.core.validator.ValidateIfNotExpired;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RetrieveValidPurchasesUseCaseTest {

    private final static String SERIALIZED_RESULT = "{HELLO}";

    RetrieveValidPurchasesUseCase useCase;

    PurchaseRepository purchaseRepository;
    @Mock
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
    @Captor
    ArgumentCaptor<List<PurchaseDTO>> purchaseListCaptor;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        fakeDetailsDataset = new ArrayList<>(3);
        fakePurchaseDataset = new ArrayList<>(3);
        fakePurchaseWithDetailsDataset = new ArrayList<>(3);

        DetailsDTO detailsDTO1 = new DetailsDTO(1, "one", 1, 1);
        DetailsDTO detailsDTO2 = new DetailsDTO(2, "two", 2, 2);
        DetailsDTO detailsDTO3 = new DetailsDTO(3, "three", 3, 3);

        LocalDateTime dateTime1 = LocalDateTime.of(2015, 1, 1, 1, 1);
        LocalDateTime dateTime2 = LocalDateTime.of(2016, 1, 1, 1, 1);
        LocalDateTime dateTime3 = LocalDateTime.of(2017, 1, 1, 1, 1);

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

    private void initUseCaseWithValidator(PurchaseValidator validator, LocalDateTime currentTime) {
        DetailsFactory detailsFactory = new DetailsFactory();
        PurchaseFactory purchaseFactory = new PurchaseFactory(validator);

        purchaseRepository = new FakePurchaseRepository(fakePurchaseDataset);
        detailsMapper = new DetailsMapper(detailsFactory);
        purchaseMapper = new PurchaseMapper(purchaseFactory, detailsMapper);
        UseCaseExecutor useCaseExecutor = new FakeMainThreadUseCaseExecutor();

        RetrieveValidPurchasesUseCaseFactory useCaseFactory = new RetrieveValidPurchasesUseCaseFactory(
                useCaseExecutor, purchaseRepository,
                purchaseMapper,
                detailsRepository,
                detailsMapper,
                serializer
        );
        useCase = useCaseFactory.make(currentTime);

        when(serializer.serializeAll(anyListOf(PurchaseDTO.class))).thenReturn(SERIALIZED_RESULT);
    }

    @Test
    public void validateAll_serializeAll() throws Exception {
        initUseCaseWithValidator(new ValidateAllValidator(), LocalDateTime.MIN);
        when(detailsRepository.getAllFromPurchaseId(anyListOf(Long.class))).thenReturn(fakeDetailsDataset);
        useCase.execute(onSuccessCallback);
        verify(serializer).serializeAll(purchaseListCaptor.capture());
        verify(onSuccessCallback).onSuccess(SERIALIZED_RESULT);

        assertEquals(fakePurchaseWithDetailsDataset, purchaseListCaptor.getValue());
    }

    @Test
    public void validateAfterExpiration_serializeOnlyLastOne() throws Exception {
        LocalDateTime june2016 = LocalDateTime.of(2016, 6, 6, 1, 1);
        initUseCaseWithValidator(new ValidateIfNotExpired(), june2016);
        List<DetailsDTO> detailsFromValidPurchase = fakeDetailsDataset.subList(2,3);
        when(detailsRepository.getAllFromPurchaseId(anyListOf(Long.class))).thenReturn(detailsFromValidPurchase);


        useCase.execute(onSuccessCallback);
        verify(serializer).serializeAll(purchaseListCaptor.capture());
        verify(onSuccessCallback).onSuccess(SERIALIZED_RESULT);

        List<PurchaseDTO> result = purchaseListCaptor.getValue();
        assertEquals(1, result.size());
        assertEquals(fakePurchaseWithDetailsDataset.get(2), result.get(0));
    }
}