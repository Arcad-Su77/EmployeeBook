package com.arcad.employeebook.elementaryClasses;


import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.capitalize;


public class Employee {
    public static final int size = 10;

    private static int count;
    private final int employeeID;
    private String firstName; //    – name - для имени
    private String lastName; //– lastName - для фамилии
    private String midleName; //– middleName - для отчества
    private int departmentID; // номер отдела
//    private double fullSalary;  // Зарплата
    private float scaleRatio; // коэффициент оклада

    public Employee(String lastName, String firstName, String midleName,
                    int departmentID, float salaryRate) {
        count++;
        this.employeeID = count;
        this.firstName = capitalize(firstName.toLowerCase());
        this.lastName = capitalize(lastName.toLowerCase());
        this.midleName = capitalize(midleName.toLowerCase());
        this.departmentID = departmentID;
        this.scaleRatio = salaryRate;
    }

    public String getEmployeeFIO() {
        return getLastName() + " " + getFirstName() + " " + getMidleName() + " ";
    }
    public String getEmployeeShortFIO() {
        return (getLastName() + " " + getFirstName().charAt(0) + ". " + getMidleName().charAt(0) + ". ");
    }
    public static int getCount() {
        return count;
    }

    public static void incCount() {
        count++;
    }
    public static void decCount() {
        count--;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = capitalize(firstName.toLowerCase());
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = capitalize(lastName.toLowerCase());
    }
    public String getMidleName() {
        return midleName;
    }
    public void setMidleName(String midleName) {
        this.midleName = capitalize(midleName.toLowerCase());
    }
    public Integer getDepartmentID() {
        return departmentID;
    }
    public int getDepartmentIndexID() {
        return departmentID;
    }
    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }
    public double getScaleRatio() { return scaleRatio; }
    public void setScaleRatio(float scaleRatio) {
        this.scaleRatio = scaleRatio;
    }
    
    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", midleName='" + midleName + '\'' +
                ", departmentID=" + departmentID +
        //        ", fullSalary=" + fullSalary +
                ", scaleRatio=" + scaleRatio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(midleName, employee.midleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, midleName);
    }
}
