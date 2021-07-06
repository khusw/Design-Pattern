package com.pp;

import java.util.List;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Employees employees = new Employees();
        employees.loadData();

        Employees employees1 = (Employees) employees.clone();
        Employees employees2 = (Employees) employees.clone();

        List<String> list1 = employees1.getEmployeeList();
        list1.add("John");

        List<String> list2 = employees2.getEmployeeList();
        list2.add("Park");

        System.out.println("employees : " + employees.getEmployeeList());
        System.out.println("employees1 : " + employees1.getEmployeeList());
        System.out.println("employees2 : " + employees2.getEmployeeList());
    }
}
