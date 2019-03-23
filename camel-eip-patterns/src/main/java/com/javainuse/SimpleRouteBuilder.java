package com.javainuse;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class SimpleRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		// from("file:/home/otto/transfer/input?noop=true").split().tokenize("\n").to("direct:endpoint1");
		// from("file:/home/otto/transfer/input?noop=true").split().tokenize("\n").to("direct:endpoint2");
		//from("file:/home/otto/transfer/input?noop=true").split().tokenize("\n").to("direct:endpoint3");
		from("file:/home/otto/transfer/input?noop=true").split().tokenize("\n").to("direct:endpoint4");

		// EIP - Content Based Router - Route the message based on the token it
		// contains.
		from("direct:endpoint1").choice().when(body().contains("javainuse1")).to("jms:queue:endpoint1.javainuse1")
				.when(body().contains("javainuse2")).to("jms:queue:endpoint1.javainuse2")
				.when(body().contains("javainuse3")).to("jms:queue:endpoint1.javainuse3").otherwise()
				.to("jms:queue:endpoint1.otherwise");

		// EIP - Message Filter - a type of Content Based routing.
		// If condition satisfied perform a task else discard it.
		from("direct:endpoint2").filter(body().contains("javainuse1")).to("jms:queue:endpoint2.javainuse1");

		// Recipient List- Dynamically set the recipients of the exchange
		// by creating the queue name at runtime
		from("direct:endpoint3").process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				String recipient = exchange.getIn().getBody().toString();
				String recipientQueue = "jms:queue:endpoint3." + recipient;
				exchange.getIn().setHeader("queue", recipientQueue);
				exchange.getIn().setHeader("ottoHeaderProperty", "some value");
				exchange.getIn().setHeader("camelJmsReplyTo", "jms:queue:some.reply.queue");
			}
		}).recipientList(header("queue"));

		// Wire Tap:Suppose get some error so send seperate copies of the message to
		// DeadLetter queue and also to target queue
		from("direct:endpoint4").wireTap("jms:queue:DeadLetterQueue").to("direct:endpoint4.target");

		from("direct:endpoint4.target").process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				// Some logic here
				String recipient = exchange.getIn().getBody().toString();
				String recipientQueue = "jms:queue:endpoint4." + recipient;
				exchange.getIn().setHeader("queue", recipientQueue);
			}
		}).recipientList(header("queue"));

	}
}