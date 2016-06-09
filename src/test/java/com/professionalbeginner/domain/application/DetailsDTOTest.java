package com.professionalbeginner.domain.application;

import com.google.common.testing.EqualsTester;
import org.junit.Test;

import static org.junit.Assert.*;

public class DetailsDTOTest {

    @Test
    public void testEquality() throws Exception {
        new EqualsTester()
                .addEqualityGroup(
                        new DetailsDTO(1, "hello", 2, 3),
                        new DetailsDTO(1, "hello", 2, 3)
                )
                .addEqualityGroup(
                        new DetailsDTO(1, "other", 2, 3),
                        new DetailsDTO(1, "other", 2, 3)
                )
                .addEqualityGroup(
                        new DetailsDTO(2, "hello", 2, 3),
                        new DetailsDTO(2, "hello", 2, 3)
                )
                .addEqualityGroup(
                        new DetailsDTO(1, "hello", 4, 3),
                        new DetailsDTO(1, "hello", 4, 3)
                )
                .addEqualityGroup(
                        new DetailsDTO(1, "hello", 2, 5),
                        new DetailsDTO(1, "hello", 2, 5)
                )
                .addEqualityGroup(
                        DetailsDTO.NULL,
                        DetailsDTO.NULL
                )
                .testEquals();
    }

    @Test(expected = NullPointerException.class)
    public void nullDescriptionField_throwException() throws Exception {
        new DetailsDTO(1, null, 2, 3);
    }
}