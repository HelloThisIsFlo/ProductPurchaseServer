package com.professionalbeginner.domain.application;

import com.professionalbeginner.domain.core.Details;
import com.professionalbeginner.domain.core.DetailsFactory;

/**
 * Mapper for Details
 */
public class DetailsMapper {

    private final DetailsFactory factory;

    public DetailsMapper(DetailsFactory factory) {
        this.factory = factory;
    }

    public DetailsDTO transform(Details details) {
        return new DetailsDTO(details.getId(), details.getDescription(), details.getQuantity(), details.getValue());
    }

    public Details transform(DetailsDTO detailsDTO) {
        return factory.make(detailsDTO.id, detailsDTO.description, detailsDTO.quantity, detailsDTO.value);
    }
}
