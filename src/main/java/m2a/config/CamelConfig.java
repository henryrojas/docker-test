package m2a.config;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.Bean;

public class CamelConfig extends CamelConfiguration {

    @Bean
    public ProducerTemplate producerTemplate() throws Exception {
        return camelContext().createProducerTemplate();
    }
}