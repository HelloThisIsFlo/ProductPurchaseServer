package com.professionalbeginner;

import com.professionalbeginner.data.detail.FakePurchaseDetailsRepository;
import com.professionalbeginner.data.purchase.FakePurchaseRepository;
import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.driven.PurchaseDetailsRepository;
import com.professionalbeginner.domain.application.driven.PurchaseRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataConfiguration {

    @Bean
    public PurchaseDetailsRepository getDetailsRepository() {
        List<DetailsDTO> fakeDataset = new ArrayList<>();
        fakeDataset.add(new DetailsDTO(1, "hello", 1, 1));
        fakeDataset.add(new DetailsDTO(2, "hello2", 12, 12));
        fakeDataset.add(new DetailsDTO(3, "hello3", 13, 13));

        return new FakePurchaseDetailsRepository(fakeDataset);
    }

    @Bean
    public PurchaseRepository getPurchaseRepository() {
        List<PurchaseDTO> fakeDataset = new ArrayList<>();

        LocalDateTime dateTime1 = LocalDateTime.of(2016, 1, 1, 1, 1);
        LocalDateTime dateTime2 = LocalDateTime.of(2016, 1, 1, 1, 2);
        LocalDateTime dateTime3 = LocalDateTime.of(2016, 1, 1, 1, 3);

        PurchaseDTO purchaseDTO1 = new PurchaseDTO(1, "type 1", dateTime1, DetailsDTO.NULL);
        PurchaseDTO purchaseDTO2 = new PurchaseDTO(2, "type 2", dateTime2, DetailsDTO.NULL);
        PurchaseDTO purchaseDTO3 = new PurchaseDTO(3, "type 3", dateTime3, DetailsDTO.NULL);

        fakeDataset.add(purchaseDTO1);
        fakeDataset.add(purchaseDTO2);
        fakeDataset.add(purchaseDTO3);

        return new FakePurchaseRepository(fakeDataset);
    }
}
