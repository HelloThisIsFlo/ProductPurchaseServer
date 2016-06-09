package com.professionalbeginner.serialisation;

import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.driven.PurchaseSerializer;
import com.professionalbeginner.domain.core.Purchase;

import java.util.List;

public class FakeSerializer implements PurchaseSerializer {

    public final static String FAKE_RESULT = "{ serialized purchases }";

    @Override
    public String serializeAll(List<PurchaseDTO> purchase) {
        return purchase == null ? "" : FAKE_RESULT;
    }
}
