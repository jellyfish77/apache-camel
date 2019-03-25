package com.javainuse.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.routepolicy.quartz.CronScheduledRoutePolicy;

import com.javainuse.processor.MyProcessor;

public class SimpleRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		CronScheduledRoutePolicy startPolicy = new CronScheduledRoutePolicy();
		startPolicy.setRouteStartTime("0 0/3 * * * ?");

		from("file:/home/otto/transfer/input?noop=true").routePolicy(startPolicy).noAutoStartup().process(new MyProcessor())
				.to("file:/home/otto/transfer/output");
	}

}