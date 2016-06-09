package com.professionalbeginner.domain.core.validator;

import com.professionalbeginner.domain.core.Purchase;

import java.time.LocalDateTime;

/**
 * Validate a purchase following a set of rules.
 * Implement to use custom set of validation rules.
 */
public interface PurchaseValidator {

    boolean validForCurrentTime(Purchase toValidate, LocalDateTime currentTime);

}
