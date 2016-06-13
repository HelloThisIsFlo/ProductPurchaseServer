package com.professionalbeginner.domain.application;

import com.professionalbeginner.domain.core.Details;
import com.professionalbeginner.domain.core.DetailsFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DetailsMapperTest {

    DetailsFactory factory;
    DetailsMapper detailsMapper;

    @Before
    public void setUp() throws Exception {
        factory = new DetailsFactory();
        detailsMapper = new DetailsMapper(factory);
    }

    @Test
    public void transformDtoToDomain() throws Exception {
        DetailsDTO dto = new DetailsDTO(1, "", 2, 3);

        Details result = detailsMapper.transform(dto);

        assertEquals(dto.id, result.getId());
        assertEquals(dto.description, result.getDescription());
        assertEquals(dto.quantity, result.getQuantity());
        assertEquals(dto.value, result.getValue(), 0.01);
    }

    @Test
    public void transformDomainToDto() throws Exception {
        Details domain = factory.make(2, "hello", 4, 5);

        DetailsDTO result = detailsMapper.transform(domain);

        assertEquals(domain.getId(), result.id);
        assertEquals(domain.getDescription(), result.description);
        assertEquals(domain.getQuantity(), result.quantity);
        assertEquals(domain.getValue(), result.value, 0.01);
    }

    @Test
    public void nullDescription_replaceWithEmpty() throws Exception {
        DetailsDTO detailsDTO = new DetailsDTO(1, null, 1,1);
        Details result = detailsMapper.transform(detailsDTO);
        assertEquals("", result.getDescription());
    }
}