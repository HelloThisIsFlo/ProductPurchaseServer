package com.professionalbeginner.serialisation;

import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.driven.PurchaseSerializer;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Florian on 09/06/16.
 */
public class ToStringMockSerializerTest {

    PurchaseSerializer serializer;

    @Before
    public void setUp() throws Exception {
        serializer = new ToStringMockSerializer();
    }

    @Test
    public void null_returnEmptyString() throws Exception {
        assertEquals("", serializer.serializeAll(null));

    }

    @Test
    public void emptyList_returnEmptyString() throws Exception {
        assertEquals("", serializer.serializeAll(new ArrayList<>()));
    }

    @Test
    public void singleItem_returnItemToString() throws Exception {
        List<PurchaseDTO> toSerialize = new ArrayList<>();
        PurchaseDTO dtoToSerialize = new PurchaseDTO(1, "type", LocalDateTime.MIN, new DetailsDTO(1, "description", 2, 2));
        toSerialize.add(dtoToSerialize);

        String expectedResult = dtoToSerialize.toString();
        assertEquals(expectedResult, serializer.serializeAll(toSerialize));
    }

    @Test
    public void multipleItems_returnItemsSeparatedWithNewLine() throws Exception {
        List<PurchaseDTO> toSerialize = new ArrayList<>();
        PurchaseDTO dtoToSerialize1 = new PurchaseDTO(1, "type", LocalDateTime.MIN, new DetailsDTO(1, "description", 2, 2));
        PurchaseDTO dtoToSerialize2 = new PurchaseDTO(3, "type2", LocalDateTime.MIN, new DetailsDTO(3, "description2", 5, 5));

        toSerialize.add(dtoToSerialize1);
        toSerialize.add(dtoToSerialize2);

        String expectedResult = dtoToSerialize1.toString() + "\n" + dtoToSerialize2.toString();
        assertEquals(expectedResult, serializer.serializeAll(toSerialize));
    }


}