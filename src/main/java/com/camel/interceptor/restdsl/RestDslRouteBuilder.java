package com.camel.interceptor.restdsl;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RestDslRouteBuilder extends RouteBuilder {

    private static final String SAMPLE_ENDPOINT = "direct:sampleEndpoint";
    private static final String LOCAL_SERVICE = "direct:localService";
    private static final String AFTER_URI_PROCESSOR = "direct:afterURLProcessor";
    private static final String AFTER_URI = "afterURI";
    private static final String ROUTE_ID = "direct-route";

    @Override
    public void configure() {

        interceptSendToEndpoint(SAMPLE_ENDPOINT)
            .log(LoggingLevel.INFO, "Interceptor triggerred for SAMPLE_ENDPOINT to check condition for AFTER_URI")
            .afterUrl(AFTER_URI_PROCESSOR);

        restConfiguration()
            .component("servlet")
            .bindingMode(RestBindingMode.auto);

        // Rest API configuration
        rest()
            .path("/api")
            .consumes("application/json")
            .produces("application/json")

            // HTTP: GET /api
            .get()
            .outType(String.class)
            .to(LOCAL_SERVICE);

        from(LOCAL_SERVICE).routeId(ROUTE_ID)
            .log(LoggingLevel.INFO, "Inside " + ROUTE_ID + ", now Calling SAMPLE_ENDPOINT")
            .to(SAMPLE_ENDPOINT)
            .log(LoggingLevel.INFO, "Returning final response")
            .to("bean:getBean"); // This will invoke the Spring bean 'getBean'*/

        from(SAMPLE_ENDPOINT)
            .log(LoggingLevel.INFO, "Request received at sampleEndpoint");

        from(AFTER_URI_PROCESSOR)
            .choice()
                .when(header(AFTER_URI).isEqualTo("true"))
                .log(LoggingLevel.INFO, "afterURI was true, now executing the afterURI logic")
            .otherwise()
                .log(LoggingLevel.INFO, "afterURI condition not met")
            .endChoice();

    }
}
