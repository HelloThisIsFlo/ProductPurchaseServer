package com.professionalbeginner.integration;

import com.professionalbeginner.ProductPurchaseServerApplication;
import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.driving.RetrieveValidPurchasesUseCaseFactory;
import com.professionalbeginner.domain.application.driving.SaveOrUpdateUseCaseFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProductPurchaseServerApplication.class)
@WebAppConfiguration
@ActiveProfiles("integration")
public class SaveAndRetrieveValidIntegrationTest {

    @Autowired
    SaveOrUpdateUseCaseFactory saveOrUpdateUseCaseFactory;
    @Autowired
    RetrieveValidPurchasesUseCaseFactory retrieveValidPurchasesUseCaseFactory;

    @Test
    public void tests() throws Exception {
        LocalDateTime validateAll = LocalDateTime.now();

        PurchaseDTO purchaseDTO1 = new PurchaseDTO(1, "hello", LocalDateTime.MAX, new DetailsDTO(1, "adsfsda", 12, 23));
        PurchaseDTO purchaseDTO2 = new PurchaseDTO(2, "hello2", LocalDateTime.MIN, new DetailsDTO(2, "zzz", 122, 232));
        PurchaseDTO purchaseDTO3 = new PurchaseDTO(3, "hello3", LocalDateTime.MAX, new DetailsDTO(3, "sss", 142, 2333));

        saveOrUpdateUseCaseFactory.make(purchaseDTO1).execute();
        saveOrUpdateUseCaseFactory.make(purchaseDTO2).execute();
        saveOrUpdateUseCaseFactory.make(purchaseDTO3).execute();

        System.out.println(retrieveValidPurchasesUseCaseFactory.make(validateAll).execute());
    }

    // TODO: 13/06/16 more integration tests
}
