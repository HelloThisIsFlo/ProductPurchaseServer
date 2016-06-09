package com.professionalbeginner.serialisation;

import com.professionalbeginner.domain.application.driven.PurchaseSerializer;
import com.professionalbeginner.domain.core.Purchase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Florian on 09/06/16.
 */
public class FakeSerializerTest {


    PurchaseSerializer serializer;

    @Before
    public void setUp() throws Exception {
        serializer = new FakeSerializer();
    }

    @Test
    public void null_returnEmptyString() throws Exception {
        assertEquals("", serializer.serializeAll(null));

    }

    @Test
    public void notNull_alwaysReturnFakeResult() throws Exception {
        assertEquals(FakeSerializer.FAKE_RESULT, serializer.serializeAll(new ArrayList<>()));
    }
}