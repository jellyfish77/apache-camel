package com.javainuse;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file:/home/otto/transfer/input?noop=true").to("file:/home/otto/transfer/output");
		//from("file:/home/otto/transfer/input?noop=true").split().tokenize("\n").to("direct:endpoint4");
	}
}