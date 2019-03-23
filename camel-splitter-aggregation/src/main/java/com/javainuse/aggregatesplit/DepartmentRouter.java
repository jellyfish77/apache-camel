package com.javainuse.aggregatesplit;

import org.apache.camel.builder.RouteBuilder;

public class DepartmentRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("direct:processDept").split(body().method("getEmployees"), new DepartmentEmployeeStrategy())
				.to("direct:processEmployee").end();

		from("direct:processEmployee").choice().when(body().method("getDepartment").isEqualTo("Finance"))
				.to("bean:employeeService?method=processITDept").when(body().method("getDepartment").isEqualTo("IT"))
				.to("bean:employeeService?method=processFinanceDept");
	}

}