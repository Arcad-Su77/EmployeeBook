package com.arcad.employeebook.service.impl;

import com.arcad.employeebook.elementaryClasses.Employee;
import com.arcad.employeebook.exception.EmployeeAlreadyAddedException;

import java.util.List;


public interface EmployeeService {

    String addEmploeey(List<String> args)
            throws EmployeeAlreadyAddedException;

    List<Employee> EmployeeAll();


    List<Employee> employeeByIDDep(int idd);

    Employee editEmployee(Integer inID, String firstName, String lastName,
                          String midleName, Float scaleRatio);

    Employee editEmployee(Integer inID,
                      String firstName, String lastName, String midleName);

    Employee editEmployee(Integer inID, Float scaleRatio);
}
