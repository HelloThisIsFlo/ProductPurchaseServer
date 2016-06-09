package com.professionalbeginner.domain.core;

import com.google.common.testing.EqualsTester;
import com.professionalbeginner.domain.application.PurchaseDTO;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Created by Florian on 09/06/16.
 */
public class PurchaseTest {

    DetailsFactory detailsFactory;
    Details details;
    Details otherDetails;
    
    PurchaseFactory factory;

    @Before
    public void setUp() throws Exception {
        detailsFactory = new DetailsFactory();
        factory = new PurchaseFactory();
        
        details = detailsFactory.make(1, "description 1", 1, 1); 
        otherDetails = detailsFactory.make(2, "description 2", 2, 2); 
    }

    @Test
    public void testEquality() throws Exception {
        new EqualsTester()
                .addEqualityGroup(
                        factory.make(1, "type", LocalDateTime.MAX, details),
                        factory.make(1, "type", LocalDateTime.MAX, details)
                )
                .addEqualityGroup(
                        factory.make(2, "type", LocalDateTime.MAX, details),
                        factory.make(2, "type", LocalDateTime.MAX, details)
                )
                .addEqualityGroup(
                        factory.make(1, "other", LocalDateTime.MAX, details),
                        factory.make(1, "other", LocalDateTime.MAX, details)
                )
                .addEqualityGroup(
                        factory.make(1, "type", LocalDateTime.MIN, details),
                        factory.make(1, "type", LocalDateTime.MIN, details)
                )
                .addEqualityGroup(
                        factory.make(1, "type", LocalDateTime.MAX, otherDetails),
                        factory.make(1, "type", LocalDateTime.MAX, otherDetails)
                )
                .testEquals();
    }

    @Test
    public void nullType_replaceWithEmpty() throws Exception {
        Purchase purchase = factory.make(1, null, LocalDateTime.MAX, details);
        assertEquals("", purchase.getProductType());
    }

    @Test
    public void nullExpire_replaceWithMIN() throws Exception {
        Purchase purchase = factory.make(1, "type", null, details);
        assertEquals(LocalDateTime.MIN, purchase.getExpires());
    }

    @Test
    public void nullDetails_replaceWithNullObject() throws Exception {
        Purchase purchase = factory.make(1, "type", LocalDateTime.MAX, null);
        assertEquals(Details.NULL, purchase.getPurchaseDetails());
    }
}