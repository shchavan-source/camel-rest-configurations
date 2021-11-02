package org.redhat.fusesource;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class CamelR extends RouteBuilder {

    @Override
    public void configure() {
        restConfiguration().component("undertow").host("localhost").port(9091).bindingMode(RestBindingMode.auto);

        rest("/say")
                .get("/hello").produces("application/json").to("direct:hello")
                .post("/bye").to("direct:bye");

        from("direct:hello")
                .log("Hello sub path")
                .transform().constant("Hello World");

        from("direct:bye")
                .log("Bye Sub path")
                .transform().constant("Bye World");
        /*from("timer:sampleTimer?period=1m&repeatCount=3")
                .log("Hello World From Java DSL");*/
    }
}
