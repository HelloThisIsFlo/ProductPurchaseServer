package com.professionalbeginner.domain.application.driven;

import com.professionalbeginner.domain.application.PurchaseDTO;

import java.util.List;

/**
 * Repository to access the Purchases
 */
public interface PurchaseRepository {
    List<PurchaseDTO> getAll();
}
