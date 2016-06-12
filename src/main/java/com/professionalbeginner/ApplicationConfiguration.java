package com.professionalbeginner;

import com.professionalbeginner.domain.application.DetailsMapper;
import com.professionalbeginner.domain.application.PurchaseMapper;
import com.professionalbeginner.domain.application.driven.PurchaseDetailsRepository;
import com.professionalbeginner.domain.application.driven.PurchaseRepository;
import com.professionalbeginner.domain.application.driven.PurchaseSerializer;
import com.professionalbeginner.domain.application.driving.RetrieveValidPurchasesUseCase;
import com.professionalbeginner.domain.application.driving.RetrieveValidPurchasesUseCaseFactory;
import com.professionalbeginner.domain.core.DetailsFactory;
import com.professionalbeginner.domain.core.PurchaseFactory;
import com.professionalbeginner.domain.core.validator.PurchaseValidator;
import com.professionalbeginner.domain.core.validator.ValidateAllValidator;
import com.professionalbeginner.domain.core.validator.ValidateIfNotExpired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

/**
 * Configuration for the main application
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public DetailsFactory getDetailsFactory() {
        return new DetailsFactory();
    }

    @Bean
    public DetailsMapper getDetailsMapper(DetailsFactory detailsFactory) {
        return new DetailsMapper(detailsFactory);
    }

    @Bean
    public PurchaseFactory getPurchaseFactory(PurchaseValidator validator) {
        return new PurchaseFactory(validator);
    }

    @Bean
    public PurchaseMapper getPurchaseMapper(PurchaseFactory purchaseFactory, DetailsMapper detailsMapper) {
        return new PurchaseMapper(purchaseFactory, detailsMapper);
    }

    @Bean
    @Profile("dev")
    public PurchaseValidator getValidator_dev() {
        return new ValidateAllValidator();
    }

    @Bean
    @Profile("integration")
    public PurchaseValidator getValidator_integration() {
        return new ValidateIfNotExpired();
    }


    @Bean
    public RetrieveValidPurchasesUseCaseFactory getRetrieveValidPurchasesUseCaseFactory(
            PurchaseRepository purchaseRepository,
            PurchaseDetailsRepository detailsRepository,
            PurchaseMapper purchaseMapper,
            DetailsMapper detailsMapper,
            PurchaseSerializer serializer) {
        return new RetrieveValidPurchasesUseCaseFactory(
                purchaseRepository,
                purchaseMapper,
                detailsRepository,
                detailsMapper,
                serializer
        );
    }
}
