package com.javainuse.aggregatesplit;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import com.javainuse.domain.Department;
import com.javainuse.domain.Employee;

public class DepartmentEmployeeStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldEmployeeExchange, Exchange newEmployeeExchange) {

		if (oldEmployeeExchange == null) {

			Employee newEmployee = newEmployeeExchange.getIn().getBody(Employee.class);
			System.out.println("Aggregate first employee: " + newEmployee);

			Department currentOrder = new Department();
			List<Employee> currentItems = new ArrayList<Employee>();

			currentItems.add(newEmployee);
			currentOrder.setEmployees(currentItems);
			currentOrder.setTotalPrice(newEmployee.getSalary());

			newEmployeeExchange.getIn().setBody(currentOrder);

			return newEmployeeExchange;
		}

		Department department = oldEmployeeExchange.getIn().getBody(Department.class);
		Employee newEmployee = newEmployeeExchange.getIn().getBody(Employee.class);

		System.out.println("Aggregate old employees: " + department);
		System.out.println("Aggregate new department: " + newEmployee);

		department.getEmployees().add(newEmployee);

		double totalPrice = department.getTotalPrice() + newEmployee.getSalary();
		department.setTotalPrice(totalPrice);

		return oldEmployeeExchange;
	}

}