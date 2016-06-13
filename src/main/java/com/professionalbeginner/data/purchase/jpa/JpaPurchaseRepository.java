package com.professionalbeginner.data.purchase.jpa;

import com.professionalbeginner.data.detail.jpa.DetailJPA;
import org.springframework.data.repository.CrudRepository;

public interface JpaPurchaseRepository extends CrudRepository<PurchaseJPA, Long> {

}
