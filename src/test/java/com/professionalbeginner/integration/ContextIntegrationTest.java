package com.professionalbeginner.integration;

import com.professionalbeginner.ProductPurchaseServerApplication;
import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.driving.RetrieveValidPurchasesUseCase;
import com.professionalbeginner.domain.application.driving.RetrieveValidPurchasesUseCaseFactory;
import com.professionalbeginner.domain.core.validator.PurchaseValidator;
import com.professionalbeginner.domain.core.validator.ValidateIfNotExpired;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProductPurchaseServerApplication.class)
@WebAppConfiguration
@ActiveProfiles("integration")
public class ContextIntegrationTest {

    @Autowired
    PurchaseValidator validator;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testProfiles() {
        String validatorClassName = validator.getClass().getSimpleName();
        assertEquals(ValidateIfNotExpired.class.getSimpleName(), validatorClassName);
    }
}
