package com.arcad.employeebook.elementaryClasses;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.capitalize;

public class Department {
   public static final int size = 5;
    private static int count;
    private final int departmentID;
    private String name; //Название отдела
    private double salary;  // Зарплата в отделе

    public Department(String name, double payment) {
        count++;
        this.departmentID = count;
        this.name = capitalize(name.toLowerCase());
        this.salary = payment;
    }

    public static void incCount() {
        count++;
    }
    public static void decCount() {
        count--;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = capitalize(name.toLowerCase());
    }
    public double getSalary() { return salary; }

    public int getDepartmentID() { return departmentID; }

    public void setSalary(int payment) {
        this.salary = payment;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentID=" + departmentID +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department that)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
