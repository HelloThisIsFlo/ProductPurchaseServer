package com.professionalbeginner.domain.application.driven;

import com.professionalbeginner.domain.application.DetailsDTO;

import java.util.List;

public interface PurchaseDetailsRepository {
    /**
     * Returns the list of DetailsDTO for the given purchase ids.
     *
     * @param purchaseIds Ids to get the details of.
     * @return List of all details, IN SAME ORDER
     */
    List<DetailsDTO> getAllFromPurchaseId(List<Long> purchaseIds);
}
