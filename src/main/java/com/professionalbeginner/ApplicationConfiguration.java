package com.professionalbeginner;

import com.professionalbeginner.domain.application.DetailsMapper;
import com.professionalbeginner.domain.application.PurchaseMapper;
import com.professionalbeginner.domain.application.driven.DetailsRepository;
import com.professionalbeginner.domain.application.driven.PurchaseRepository;
import com.professionalbeginner.domain.application.driven.PurchaseSerializer;
import com.professionalbeginner.domain.application.driving.RetrieveValidPurchasesUseCaseFactory;
import com.professionalbeginner.domain.application.driving.SaveOrUpdateUseCaseFactory;
import com.professionalbeginner.domain.core.DetailsFactory;
import com.professionalbeginner.domain.core.PurchaseFactory;
import com.professionalbeginner.domain.core.validator.PurchaseValidator;
import com.professionalbeginner.domain.core.validator.ValidateAllValidator;
import com.professionalbeginner.domain.core.validator.ValidateIfNotExpired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

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
    public PurchaseValidator getValidator() {
        return new ValidateIfNotExpired();
    }


    @Bean
    public RetrieveValidPurchasesUseCaseFactory getRetrieveValidPurchasesUseCaseFactory(
            PurchaseRepository purchaseRepository,
            DetailsRepository detailsRepository,
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

    @Bean
    public SaveOrUpdateUseCaseFactory getSaveOrUpdateUseCaseFactory(
            PurchaseRepository purchaseRepository,
            DetailsRepository detailsRepository,
            PurchaseMapper purchaseMapper,
            DetailsMapper detailsMapper) {
        return new SaveOrUpdateUseCaseFactory(purchaseRepository, detailsRepository, purchaseMapper, detailsMapper);
    }
}
