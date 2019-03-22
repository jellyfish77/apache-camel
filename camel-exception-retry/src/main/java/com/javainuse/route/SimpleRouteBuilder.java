package com.javainuse.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import com.javainuse.exception.CamelCustomException;
import com.javainuse.processor.MyProcessor;
import com.javainuse.processor.RedeliveryProcessor;

public class SimpleRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		onException(CamelCustomException.class).process(new Processor() {

			public void process(Exchange exchange) throws Exception {
				System.out.println("handling ex");
			}
			// }).onRedelivery(new
			// RedeliveryProcessor()).redeliveryPolicyRef("testRedeliveryPolicyProfile")
			// .log("Received body ${body}").handled(true);
		}).onRedelivery(new RedeliveryProcessor()).redeliveryPolicyRef("testRedeliveryPolicyProfile").log("Received body ${body}").handled(true);

		from("file:/home/otto/transfer/input?noop=true").process(new MyProcessor())
				.to("file:/home/otto/transfer/output");
	}

}
