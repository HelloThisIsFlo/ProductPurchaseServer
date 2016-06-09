package com.professionalbeginner.domain.core.validator;

import com.professionalbeginner.domain.core.Purchase;

import java.time.LocalDateTime;

/**
 * Created by Florian on 09/06/16.
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
