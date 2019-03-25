package com.javainuse.domain;

import java.util.List;

public class Department {
    
    
    private List<Employee> employees;
    
    private double totalPrice;
        

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }    

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Department");
        builder.append(", Employees=");
        builder.append(employees);
        builder.append(", totalSalary=");
        builder.append(totalPrice);
        builder.append("]");
        return builder.toString();
    }

}