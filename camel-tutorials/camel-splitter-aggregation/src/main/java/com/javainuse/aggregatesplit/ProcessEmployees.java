package com.javainuse.aggregatesplit;

import com.javainuse.domain.Employee;

public class ProcessEmployees {

	public Employee processITDept(Employee employee)
			throws InterruptedException {

		System.out.println("handle employee department:" + employee);
		employee.setSalary(10000);

		System.out.println("IT dept employee processed");

		return employee;
	}

	public Employee processFinanceDept(Employee employee)
			throws InterruptedException {

		System.out.println("handle employee department:" + employee);
		employee.setSalary(5000);

		System.out.println("Finance dept employee processed");

		return employee;
	}
}