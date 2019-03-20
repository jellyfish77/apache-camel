package com.javainuse;
import org.apache.camel.builder.RouteBuilder;

public class SimpleRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
        from("file:/home/otto/transfer/input?noop=true").to("file:/home/otto/transfer/output");
		
	}
}
