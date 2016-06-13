package com.professionalbeginner.data.purchase.jpa;

import com.professionalbeginner.data.detail.jpa.DetailJPA;
import com.professionalbeginner.data.detail.jpa.JpaDetailMapper;
import com.professionalbeginner.data.detail.jpa.JpaDetailRepository;
import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.driven.DetailsRepository;
import com.professionalbeginner.domain.application.driven.PurchaseRepository;
import com.professionalbeginner.domain.core.Purchase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian on 13/06/16.
 */
public class JpaInMemoryPurchaseRepository implements PurchaseRepository {

    private final JpaPurchaseRepository jpaRepo;
    private final JpaPurchaseMapper jpaMapper;


    public JpaInMemoryPurchaseRepository(JpaPurchaseRepository jpaRepo, JpaPurchaseMapper jpaMapper) {
        this.jpaRepo = jpaRepo;
        this.jpaMapper = jpaMapper;
    }

    @Override
    public List<PurchaseDTO> getAll() {
        List<PurchaseDTO> result = new ArrayList<>();
        Iterable<PurchaseJPA> allPurchase = jpaRepo.findAll();
        for (PurchaseJPA purchaseJPA : allPurchase) {
            PurchaseDTO dto = jpaMapper.transform(purchaseJPA);
            result.add(dto);
        }

        return result;
    }

    @Override
    public Long saveOrUpdate(PurchaseDTO toSave) {
        if (toSave == null) {
            return -1L;
        }
        PurchaseJPA jpa = jpaMapper.transform(toSave);
        PurchaseJPA withId = jpaRepo.save(jpa);

        return withId.getId();
    }

}
