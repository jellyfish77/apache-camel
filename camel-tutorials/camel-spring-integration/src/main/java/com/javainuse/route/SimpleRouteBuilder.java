package com.javainuse.route;

import org.apache.camel.builder.RouteBuilder;
import com.javainuse.processor.MyProcessor;

public class SimpleRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:/home/otto/transfer/input?noop=true").process(new MyProcessor()).to("file:/home/otto/transfer/output");
    }

}