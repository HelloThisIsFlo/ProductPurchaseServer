package com.professionalbeginner.domain.application.driving;

import com.professionalbeginner.data.detail.FakePurchaseDetailsRepository;
import com.professionalbeginner.data.purchase.FakePurchaseRepository;
import com.professionalbeginner.domain.application.DetailsMapper;
import com.professionalbeginner.domain.application.PurchaseMapper;
import com.professionalbeginner.domain.application.driven.PurchaseDetailsRepository;
import com.professionalbeginner.domain.application.driven.PurchaseRepository;
import com.professionalbeginner.domain.application.driven.PurchaseSerializer;
import com.professionalbeginner.domain.core.DetailsFactory;
import com.professionalbeginner.domain.core.PurchaseFactory;
import com.professionalbeginner.domain.core.validator.PurchaseValidator;
import com.professionalbeginner.domain.core.validator.ValidateAllValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Florian on 09/06/16.
 */
public class RetrieveValidPurchasesUseCaseTest {

    RetrieveValidPurchasesUseCase useCase;

    PurchaseRepository purchaseRepository;
    PurchaseDetailsRepository detailsRepository;
    PurchaseMapper purchaseMapper;
    DetailsMapper detailsMapper;
    PurchaseValidator validator;

    @Mock
    PurchaseSerializer serializer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        validator = new ValidateAllValidator();

        DetailsFactory detailsFactory = new DetailsFactory();
        PurchaseFactory purchaseFactory = new PurchaseFactory(validator);

        purchaseRepository = new FakePurchaseRepository(new ArrayList<>());
        detailsMapper = new DetailsMapper(detailsFactory);
        purchaseMapper = new PurchaseMapper(purchaseFactory, detailsMapper);
        detailsRepository = new FakePurchaseDetailsRepository(new ArrayList<>());

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
    public void name() throws Exception {

    }
}