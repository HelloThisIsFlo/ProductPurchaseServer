package com.professionalbeginner;

import com.professionalbeginner.domain.application.driven.PurchaseSerializer;
import com.professionalbeginner.serialisation.FakeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Florian on 09/06/16.
 */
@Configuration
public class SerializationConfiguration {

    @Bean
    public PurchaseSerializer getSerializer() {
        return new FakeSerializer();
    }
}
