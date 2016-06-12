package com.professionalbeginner.domain.application;

import com.google.common.testing.EqualsTester;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class PurchaseDTOTest {

    private DetailsDTO details;
    private DetailsDTO otherDetails;

    @Before
    public void setUp() throws Exception {
        details = new DetailsDTO(1, "description", 1, 1);
        otherDetails = new DetailsDTO(2, "description 2", 2, 2);
    }

    @Test
    public void testEquality() throws Exception {
        new EqualsTester()
                .addEqualityGroup(
                        new PurchaseDTO(1, "type", LocalDateTime.MAX, details),
                        new PurchaseDTO(1, "type", LocalDateTime.MAX, details)
                )
                .addEqualityGroup(
                        new PurchaseDTO(2, "type", LocalDateTime.MAX, details),
                        new PurchaseDTO(2, "type", LocalDateTime.MAX, details)
                )
                .addEqualityGroup(
                        new PurchaseDTO(1, "other", LocalDateTime.MAX, details),
                        new PurchaseDTO(1, "other", LocalDateTime.MAX, details)
                )
                .addEqualityGroup(
                        new PurchaseDTO(1, "type", LocalDateTime.MIN, details),
                        new PurchaseDTO(1, "type", LocalDateTime.MIN, details)
                )
                .addEqualityGroup(
                        new PurchaseDTO(1, "type", LocalDateTime.MAX, otherDetails),
                        new PurchaseDTO(1, "type", LocalDateTime.MAX, otherDetails)
                )
                .testEquals();
    }

}