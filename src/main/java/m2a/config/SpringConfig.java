package m2a.config;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SpringPlaceHolder.class, CamelConfig.class})
@ComponentScan("m2a")
public class SpringConfig {

    public static final Region REGION = Region.getRegion(Regions.EU_WEST_1);

    @Bean
    public AmazonSQS amazonSqs() {
        AmazonSQSClient amazonSqs = new AmazonSQSClient();
        amazonSqs.setRegion(REGION);
        return amazonSqs;
    }
}
