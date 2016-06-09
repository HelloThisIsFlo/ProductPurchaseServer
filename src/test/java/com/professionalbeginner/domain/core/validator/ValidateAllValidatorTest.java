package com.professionalbeginner.domain.core.validator;

import com.professionalbeginner.domain.core.Details;
import com.professionalbeginner.domain.core.Purchase;
import com.professionalbeginner.domain.core.PurchaseFactory;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ValidateAllValidatorTest {

    PurchaseValidator validator;
    Purchase purchase;

    @Before
    public void setUp() throws Exception {
        validator = new ValidateAllValidator();

        PurchaseFactory factory = new PurchaseFactory();
        purchase = factory.make(1, "asdf", LocalDateTime.MIN, Details.NULL);

    }

    @Test
    public void nullPurchase_doNotValidate() throws Exception {
        boolean valid = validator.validForCurrentTime(null, LocalDateTime.MAX);
        assertFalse(valid);
    }

    @Test
    public void nullObjectPurchase_validate() throws Exception {
        boolean valid = validator.validForCurrentTime(Purchase.NULL, LocalDateTime.MAX);
        assertTrue(valid);
    }

    @Test
    public void nullCurrentTime_doNotValidate() throws Exception {
        boolean valid = validator.validForCurrentTime(purchase, null);
        assertFalse(valid);
    }

    @Test
    public void nonNullPurchaseAndTime_validate() throws Exception {
        assertTrue(validator.validForCurrentTime(purchase, LocalDateTime.MAX));
    }
}