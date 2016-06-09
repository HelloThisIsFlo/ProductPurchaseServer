package com.professionalbeginner.domain.application.driven;

import com.professionalbeginner.domain.core.Purchase;

import java.util.List;

/**
 * Created by Florian on 09/06/16.
 */
public interface PurchaseSerializer {

    String serializeAll(List<Purchase> purchase);

}
