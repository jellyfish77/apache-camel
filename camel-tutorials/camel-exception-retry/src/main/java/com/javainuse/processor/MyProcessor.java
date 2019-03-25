package com.javainuse.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import com.javainuse.exception.CamelCustomException;

public class MyProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		String a = exchange.getIn().getBody(String.class);
		System.out.println("hello " + a.trim());
		if (a.trim().equalsIgnoreCase("test")) {			
			throw new CamelCustomException();
		}
	}

}