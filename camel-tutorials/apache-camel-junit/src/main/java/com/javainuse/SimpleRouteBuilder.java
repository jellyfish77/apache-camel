package com.javainuse;

import org.apache.camel.builder.RouteBuilder;

public class SimpleRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("{{route.start}}").split().tokenize("\n").to("{{route.end}}");
    }

}