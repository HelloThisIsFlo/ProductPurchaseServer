package com.professionalbeginner.data.detail.jpa;

import com.professionalbeginner.domain.application.DetailsDTO;
import org.springframework.stereotype.Component;

/**
 * Created by Florian on 13/06/16.
 */
@Component
public class JpaDetailMapper {

    public DetailsDTO transform(DetailJPA detailJPA) {
        if (detailJPA == null) {
            return DetailsDTO.NULL;
        }
        return new DetailsDTO(
                detailJPA.getDomainId(),
                detailJPA.getDescription(),
                detailJPA.getQuantity(),
                detailJPA.getValue()
        );
    }

    public DetailJPA transform(DetailsDTO detailsDTO) {
        return new DetailJPA(
                detailsDTO.id,
                detailsDTO.description,
                detailsDTO.quantity,
                detailsDTO.value
        );
    }
}
