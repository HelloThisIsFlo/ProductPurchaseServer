package com.professionalbeginner.data.detail.jpa;

import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.driven.DetailsRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian on 13/06/16.
 */
public class JpaInMemoryDetailsRepository implements DetailsRepository {

    private final JpaDetailRepository jpaRepo;
    private final JpaDetailMapper jpaMapper;

    public JpaInMemoryDetailsRepository(JpaDetailRepository jpaRepo, JpaDetailMapper jpaMapper) {
        this.jpaRepo = jpaRepo;
        this.jpaMapper = jpaMapper;
    }

    @Override
    public List<DetailsDTO> getAllFromPurchaseId(List<Long> purchaseIds) {

        Iterable<DetailJPA> allDetails = jpaRepo.findAll();

        List<DetailsDTO> result = new ArrayList<>();

        for (Long id : purchaseIds) {
            DetailJPA jpa = jpaRepo.findByDomainId(id);
            result.add(jpaMapper.transform(jpa));
        }

        return result;

    }

    @Override
    public void saveOrUpdate(DetailsDTO toSave) {

        long id = toSave.id;

        DetailJPA existingRecord = jpaRepo.findByDomainId(id);
        if (existingRecord != null) {
            jpaRepo.delete(id);
        }



        DetailJPA jpa = jpaMapper.transform(toSave);
        jpaRepo.save(jpa);
    }
}
