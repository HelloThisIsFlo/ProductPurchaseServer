package com.professionalbeginner.data.detail.jpa;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Florian on 13/06/16.
 */
public interface JpaDetailRepository extends CrudRepository<DetailJPA, Long> {

    DetailJPA findByDomainId(Long domainId);

}
