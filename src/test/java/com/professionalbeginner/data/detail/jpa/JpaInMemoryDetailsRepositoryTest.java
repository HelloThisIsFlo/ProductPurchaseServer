package com.professionalbeginner.data.detail.jpa;

import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.driven.DetailsRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class JpaInMemoryDetailsRepositoryTest {

    DetailsRepository detailsRepository;
    @Mock
    JpaDetailRepository jpaDetailRepository;
    JpaDetailMapper jpaMapper;
    DetailsDTO toSave;
    DetailJPA toSaveJPA;
    List<DetailJPA> fakeDatasetJPA;
    private List<DetailsDTO> fakeDataset;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        toSave = new DetailsDTO(2, "hello", 1, 23.3);
        toSaveJPA = new DetailJPA(2, "hello", 1, 23.3);
        jpaMapper = new JpaDetailMapper();
        detailsRepository = new JpaInMemoryDetailsRepository(jpaDetailRepository, jpaMapper);

        fakeDatasetJPA = new ArrayList<>();

        DetailJPA detailsJPA1 = new DetailJPA(1, "description 1", 1, 1);
        DetailJPA detailsJPA2 = new DetailJPA(2, "description 2", 2, 2);
        DetailJPA detailsJPA3 = new DetailJPA(3, "description 3", 3, 3);

        fakeDatasetJPA.add(detailsJPA1);
        fakeDatasetJPA.add(detailsJPA2);
        fakeDatasetJPA.add(detailsJPA3);

        fakeDataset = new ArrayList<>();

        DetailsDTO detailsDTO1 = new DetailsDTO(1, "description 1", 1, 1);
        DetailsDTO detailsDTO2 = new DetailsDTO(2, "description 2", 2, 2);
        DetailsDTO detailsDTO3 = new DetailsDTO(3, "description 3", 3, 3);

        fakeDataset.add(detailsDTO1);
        fakeDataset.add(detailsDTO2);
        fakeDataset.add(detailsDTO3);

        when(jpaDetailRepository.findAll()).thenReturn(fakeDatasetJPA);
        when(jpaDetailRepository.findByDomainId(1L)).thenReturn(detailsJPA1);
        when(jpaDetailRepository.findByDomainId(2L)).thenReturn(detailsJPA2);
        when(jpaDetailRepository.findByDomainId(3L)).thenReturn(detailsJPA3);
    }

    @Test
    public void save_delegateToJpaRepo() throws Exception {
        detailsRepository.saveOrUpdate(toSave);

        verify(jpaDetailRepository).save(toSaveJPA);
    }

    @Test
    public void updateExisting_eraseValueInJpa_saveNewValue() throws Exception {
        long existingId = fakeDatasetJPA.get(0).getDomainId();

        detailsRepository.saveOrUpdate(toSave);

        DetailsDTO newValue = new DetailsDTO(existingId, "new description", 23, 2112.44);
        DetailJPA newValueJpa = new DetailJPA(existingId, "new description", 23, 2112.44);
        detailsRepository.saveOrUpdate(newValue);

        verify(jpaDetailRepository).findByDomainId(existingId);
        verify(jpaDetailRepository).delete(existingId);
        verify(jpaDetailRepository).save(newValueJpa);
    }

    @Test
    public void allIds_returnAllDetails() throws Exception {
        List<Long> purchaseIds = makeIdList(1, 2, 3);

        assertEquals(fakeDataset, detailsRepository.getAllFromPurchaseId(purchaseIds));
    }

    @Test
    public void first2Ids_returnFirst2Details() throws Exception {
        List<Long> purchaseIds = makeIdList(1, 2);

        assertEquals(fakeDataset.subList(0, 2), detailsRepository.getAllFromPurchaseId(purchaseIds));
    }

    @Test
    public void nonExistingId_returnNullObject() throws Exception {
        List<Long> purchaseIds = makeIdList(23);

        List<DetailsDTO> expectedResult = new ArrayList<>();
        expectedResult.add(DetailsDTO.NULL);

        assertEquals(expectedResult, detailsRepository.getAllFromPurchaseId(purchaseIds));
    }

    @Test
    public void keepOrder() throws Exception {
        List<Long> purchaseIds = makeIdList(1,3,2);

        List<DetailsDTO> result = detailsRepository.getAllFromPurchaseId(purchaseIds);

        assertEquals(3, result.size());

        // Check order
        assertEquals(fakeDataset.get(0), result.get(0));
        assertEquals(fakeDataset.get(1), result.get(2));
        assertEquals(fakeDataset.get(2), result.get(1));
    }

    private List<Long> makeIdList(int... ids) {
        List<Long> result = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            result.add((long) ids[i]);
        }
        return result;
    }
}