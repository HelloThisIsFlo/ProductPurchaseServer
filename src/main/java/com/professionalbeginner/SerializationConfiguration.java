package com.professionalbeginner;

import com.professionalbeginner.domain.application.driven.PurchaseSerializer;
import com.professionalbeginner.serialisation.FakeSerializer;
import com.professionalbeginner.serialisation.ToStringMockSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class SerializationConfiguration {

    @Bean
    @Profile("integration")
    public PurchaseSerializer getSerializer() {
        return new ToStringMockSerializer();
    }
}
