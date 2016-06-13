package com.professionalbeginner.domain.application.driven;

import com.professionalbeginner.domain.application.PurchaseDTO;

import java.util.List;

/**
 * Repository to access the Purchases
 */
public interface PurchaseRepository {
    List<PurchaseDTO> getAll();

    /**
     * Saves or update an existing Purchase DTO
     * @param toSave Purchase DTO to be saved, or updated if id already present
     * @return id of the saved PurchaseDTO
     */
    Long saveOrUpdate(PurchaseDTO toSave);
}
