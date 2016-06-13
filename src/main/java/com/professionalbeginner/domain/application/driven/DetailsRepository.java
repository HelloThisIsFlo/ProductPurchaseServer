package com.professionalbeginner.domain.application.driven;

import com.professionalbeginner.domain.application.DetailsDTO;

import java.util.List;

public interface DetailsRepository {
    /**
     * Returns the list of DetailsDTO for the given purchase ids.
     *
     * @param purchaseIds Ids to get the details of.
     * @return List of all details, IN SAME ORDER
     */
    List<DetailsDTO> getAllFromPurchaseId(List<Long> purchaseIds);

    /**
     * Saves or update an existing Details DTO.
     * DetailsDTO needs to provide a valid Id.
     *
     * @param toSave Details DTO to be saved, or updated if id already present
     * @throws InvalidIdException If DetailsDTO id is invalid (<=0)
     */
    void saveOrUpdate(DetailsDTO toSave);
}
