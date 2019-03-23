package com.javainuse.aggregatesplit;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javainuse.domain.Department;
import com.javainuse.domain.Employee;

public class DepartmentApp {
    
    public static void main(String[] args) {
        
    
        try {
            ApplicationContext springCtx = new ClassPathXmlApplicationContext(
                    "camel-context.xml");

            CamelContext context = springCtx.getBean("departmentCtx",
                    CamelContext.class);
            
            context.start();
                
            ProducerTemplate producerTemplate = context.createProducerTemplate();
            
            List<Employee> employees = new ArrayList<Employee>();
            employees.add(new Employee("1", "emp1", "Finance"));
            employees.add(new Employee("2", "emp2", "IT"));
            employees.add(new Employee("2", "emp3", "IT"));
            
            Department dept = new Department();
            dept.setEmployees(employees);
                        
            Department deptDetails = producerTemplate.requestBody(
                    "direct:processDept", dept, Department.class);
            
            System.out.println("Department Details - "+deptDetails);

            context.stop();
            
        } catch (Exception e) {
            
            e.printStackTrace();
        
        } 

    }

}
