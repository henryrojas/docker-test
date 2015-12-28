package m2a;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RaccoonRouteTest extends CamelTestSupport {

    public static final String IN_ENDPOINT = "direct:start";
    public static final String OUT_ENDPOINT = "mock:out";

    @EndpointInject(uri = OUT_ENDPOINT) MockEndpoint outEndpoint;

    @Produce ProducerTemplate producerTemplate;

    @Override
    protected RouteBuilder createRouteBuilder() throws IllegalAccessException {
        return new DockerRoute(IN_ENDPOINT, OUT_ENDPOINT);
    }

    @Test
    public void itShouldReceiveAndSendMessage() throws Exception {
        producerTemplate.sendBody(IN_ENDPOINT, "json message");

        outEndpoint.expectedMessageCount(1);
        outEndpoint.assertIsSatisfied();

        List<Exchange> exchanges = outEndpoint.getExchanges();

        assertEquals("json message", exchanges.get(0).getIn().getBody());
    }
}