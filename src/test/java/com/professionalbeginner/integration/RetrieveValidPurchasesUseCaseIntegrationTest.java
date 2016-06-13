package com.professionalbeginner.integration;

import com.professionalbeginner.ProductPurchaseServerApplication;
import com.professionalbeginner.data.detail.jpa.DetailJPA;
import com.professionalbeginner.data.detail.jpa.JpaDetailRepository;
import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.driving.RetrieveValidPurchasesUseCase;
import com.professionalbeginner.domain.application.driving.RetrieveValidPurchasesUseCaseFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProductPurchaseServerApplication.class)
@WebAppConfiguration
@ActiveProfiles("integration")
public class RetrieveValidPurchasesUseCaseIntegrationTest {

    @Autowired
    RetrieveValidPurchasesUseCaseFactory useCaseFactory;

    @Autowired
    JpaDetailRepository jpaDetailRepository;

    @Before
    public void setUp() throws Exception {
        // Init the database
        jpaDetailRepository.deleteAll();

        jpaDetailRepository.save(new DetailJPA(1, "hello", 1, 1));
        jpaDetailRepository.save(new DetailJPA(2, "hello2", 12, 12));
        jpaDetailRepository.save(new DetailJPA(3, "hello3", 13, 13));

    }

    @Test
    public void validateOnlyLastOne() throws Exception {
        LocalDateTime validateOnlyLastOne = LocalDateTime.of(2016, 1, 1, 1, 2, 30);

        RetrieveValidPurchasesUseCase useCase = useCaseFactory.make(validateOnlyLastOne);
        String result = useCase.execute();

        PurchaseDTO purchaseDTO = new PurchaseDTO(
                3,
                "type 3",
                LocalDateTime.of(2016, 1, 1, 1, 3),
                new DetailsDTO(3, "hello3", 13, 13)
        );

        String expectedResult = purchaseDTO.toString();

        assertEquals(expectedResult, result);
    }

    @Test
    public void validateAll() throws Exception {
        LocalDateTime validateAll = LocalDateTime.MIN;

        RetrieveValidPurchasesUseCase useCase = useCaseFactory.make(validateAll);
        String result = useCase.execute();

        String expectedResult = makeValidateAllExpectedResult();

        assertEquals(expectedResult, result);
    }

    private String makeValidateAllExpectedResult() {
        PurchaseDTO purchaseDTO1 = new PurchaseDTO(1,
                "type 1",
                LocalDateTime.of(2016, 1, 1, 1, 1),
                new DetailsDTO(1, "hello", 1, 1)
        );
        PurchaseDTO purchaseDTO2 = new PurchaseDTO(2,
                "type 2",
                LocalDateTime.of(2016, 1, 1, 1, 2),
                new DetailsDTO(2, "hello2", 12, 12)
        );
        PurchaseDTO purchaseDTO3 = new PurchaseDTO(
                3,
                "type 3",
                LocalDateTime.of(2016, 1, 1, 1, 3),
                new DetailsDTO(3, "hello3", 13, 13)
        );

        return purchaseDTO1.toString() + "\n" +
                purchaseDTO2.toString() + "\n" +
                purchaseDTO3.toString();
    }
}
