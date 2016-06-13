package com.professionalbeginner.integration;

import com.professionalbeginner.ProductPurchaseServerApplication;
import com.professionalbeginner.data.detail.jpa.DetailJPA;
import com.professionalbeginner.data.detail.jpa.JpaDetailRepository;
import com.professionalbeginner.data.detail.jpa.JpaDetailMapper;
import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.driving.SaveOrUpdateUseCase;
import com.professionalbeginner.domain.application.driving.SaveOrUpdateUseCaseFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProductPurchaseServerApplication.class)
@WebAppConfiguration
@ActiveProfiles("integration")
public class SaveOrUpdateUseCaseIntegrationTest {

    @Autowired
    SaveOrUpdateUseCaseFactory factory;

    @Autowired
    JpaDetailRepository jpaDetailRepository;
    @Autowired
    JpaDetailMapper jpaMapper;

    PurchaseDTO toSave;
    DetailsDTO toSaveDetails;

    @Before
    public void setUp() throws Exception {
        jpaDetailRepository.deleteAll();
        toSaveDetails = new DetailsDTO(1, "sdaf", 1, 223);
        toSave = new PurchaseDTO(1, "sdf", LocalDateTime.MIN, toSaveDetails);
    }

    @Test
    public void save() throws Exception {
        SaveOrUpdateUseCase useCase = factory.make(toSave);
        useCase.execute();
        assertRepoHasOnlyOneElementEqualToArgument(toSaveDetails);
    }

    private void assertRepoHasOnlyOneElementEqualToArgument(DetailsDTO shouldEqual) {
        Iterator<DetailJPA> iterator = jpaDetailRepository.findAll().iterator();
        assertTrue(iterator.hasNext());

        DetailJPA jpaElement = iterator.next();
        assertFalse(iterator.hasNext());

        DetailsDTO dtoElement = jpaMapper.transform(jpaElement);
        assertEquals(shouldEqual, dtoElement);
    }


    // TODO: 13/06/16 more integration tests
}