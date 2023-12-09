package com.arcad.employeebook.service.impl;

import com.arcad.employeebook.elementaryClasses.Employee;
import com.arcad.employeebook.service.exception.EmployeeAlreadyAddedException;

import java.util.List;


public interface EmployeeService {

    String addEmploeey(List<String> args)
            throws EmployeeAlreadyAddedException;

    List<Employee> EmployeeAll();
    List<Employee> EmployeeByIDDep(String idd);

}
