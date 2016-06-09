package com.professionalbeginner.domain.core.validator;

import com.professionalbeginner.domain.core.Purchase;

import java.time.LocalDateTime;

/**
 * Validate an order if it has not expired.
 */
public class ValidateIfNotExpired implements PurchaseValidator {


    @Override
    public boolean validForCurrentTime(Purchase toValidate, LocalDateTime currentTime) {
        if (toValidate == null || currentTime == null) {
            return false;
        }

        LocalDateTime expirationDate = toValidate.getExpires();
        return currentTime.isBefore(expirationDate);
    }
}
