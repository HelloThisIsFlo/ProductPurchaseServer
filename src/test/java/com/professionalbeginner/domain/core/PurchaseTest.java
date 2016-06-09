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
}