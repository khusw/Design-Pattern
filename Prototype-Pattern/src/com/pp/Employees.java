package com.pp;

import java.util.ArrayList;
import java.util.List;

public class Employees implements Cloneable{
    private final List<String> employeeList;

    public Employees() {
        employeeList = new ArrayList<>();
    }

    public Employees(List<String> employeeList) {
        this.employeeList = employeeList;
    }

    public void loadData() {
        // DB 로 부터 데이터를 읽어와 리스트에 넣었다고 가정
        employeeList.add("Tom");
        employeeList.add("David");
        employeeList.add("Lisa");
    }

    public List<String> getEmployeeList() {
        return employeeList;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        List<String> tempList = new ArrayList<>(this.employeeList);
        return new Employees(tempList);
    }
}
