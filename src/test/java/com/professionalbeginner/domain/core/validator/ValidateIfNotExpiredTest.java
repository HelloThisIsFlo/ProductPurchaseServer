package com.professionalbeginner.domain.core.validator;

import com.professionalbeginner.domain.core.Details;
import com.professionalbeginner.domain.core.Purchase;
import com.professionalbeginner.domain.core.PurchaseFactory;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Created by Florian on 09/06/16.
 */
public class ValidateIfNotExpiredTest {

    PurchaseValidator validator;
    Purchase beforeCurrentDate;
    Purchase afterCurrentDate;
    Purchase atExpectedDate;

    LocalDateTime currentDate;

    @Before
    public void setUp() throws Exception {
        currentDate = LocalDateTime.of(2015, 1,1,1,1);
        LocalDateTime afterDate = LocalDateTime.of(2016, 1,1,1,1);
        LocalDateTime beforeDate = LocalDateTime.of(2014, 1,1,1,1);

        validator = new ValidateIfNotExpired();

        PurchaseFactory factory = new PurchaseFactory(validator);

        beforeCurrentDate = factory.make(1, "type 1", beforeDate, Details.NULL);
        afterCurrentDate = factory.make(2, "type 2", afterDate, Details.NULL);
        atExpectedDate = factory.make(3, "type 3", currentDate, Details.NULL);
    }

    @Test
    public void nullPurchase_doNotValidate() throws Exception {
        assertFalse(validator.validForCurrentTime(null, currentDate));
    }

    @Test
    public void nullDate_doNotValidate() throws Exception {
        assertFalse(validator.validForCurrentTime(beforeCurrentDate, null));
    }

    @Test
    public void nullObjectPurchase_doNotValidate() throws Exception {
        assertFalse(validator.validForCurrentTime(Purchase.NULL, currentDate));
    }

    @Test
    public void expiresAfterCurrentDate_Validate() throws Exception {
        assertTrue(validator.validForCurrentTime(afterCurrentDate, currentDate));
    }

    @Test
    public void expiresBeforeCurrentDate_doNotValidate() throws Exception {
        assertFalse(validator.validForCurrentTime(beforeCurrentDate, currentDate));
    }
}