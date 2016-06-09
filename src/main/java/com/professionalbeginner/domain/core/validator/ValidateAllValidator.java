package com.professionalbeginner.domain.core.validator;

import com.professionalbeginner.domain.core.Purchase;

import java.time.LocalDateTime;

/**
 * Purchase validator that validate all non-null purchases
 */
public class ValidateAllValidator implements PurchaseValidator {

    @Override
    public boolean validForCurrentTime(Purchase toValidate, LocalDateTime currentTime) {
        if (toValidate == null || currentTime == null) {
            return false;
        }
        return true;
    }
}
