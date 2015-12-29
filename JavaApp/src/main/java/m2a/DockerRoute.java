package m2a;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DockerRoute extends RouteBuilder {

    private final String inEndpoint;
    private final String outEndpoint;

    @Autowired
    public DockerRoute(@Value("${in.endpoint}") String inEndpoint, @Value("${out.endpoint}") String outEndpoint) {
        this.inEndpoint = inEndpoint;
        this.outEndpoint = outEndpoint;
    }

    @Override
    public void configure() throws Exception {
        from(inEndpoint)
                .to(outEndpoint)
                .end();
    }
}