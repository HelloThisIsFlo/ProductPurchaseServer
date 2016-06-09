package com.professionalbeginner.domain.core;

import com.google.common.testing.EqualsTester;
import com.professionalbeginner.domain.application.DetailsDTO;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DetailsTest {

    DetailsFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new DetailsFactory();
    }

    @Test
    public void testEquality() throws Exception {
        new EqualsTester()
                .addEqualityGroup(
                        factory.make(1, "hello", 2, 3),
                        factory.make(1, "hello", 2, 3)
                )
                .addEqualityGroup(
                        factory.make(1, "other", 2, 3),
                        factory.make(1, "other", 2, 3)
                )
                .addEqualityGroup(
                        factory.make(2, "hello", 2, 3),
                        factory.make(2, "hello", 2, 3)
                )
                .addEqualityGroup(
                        factory.make(1, "hello", 4, 3),
                        factory.make(1, "hello", 4, 3)
                )
                .addEqualityGroup(
                        factory.make(1, "hello", 2, 5),
                        factory.make(1, "hello", 2, 5)
                )
                .addEqualityGroup(
                        Details.NULL,
                        Details.NULL
                )
                .testEquals();
    }



    @Test
    public void nullDescriptionField_replaceWithEmpty() throws Exception {
        Details details = factory.make(1, null, 2, 3);
        assertEquals("", details.getDescription());
    }
}