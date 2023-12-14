package com.arcad.employeebook.service.impl;

import com.arcad.employeebook.elementaryClasses.Department;
import com.arcad.employeebook.elementaryClasses.Employee;
import com.arcad.employeebook.exception.DepartmentAlreadyAddedException;
import com.arcad.employeebook.exception.InputArgsErrorException;

import java.util.List;

public interface DepartmentService {
    List<Department> allDepartment();

    List<Employee> employeeByDepartment(int idd);

    /**
     * @param name   Название отдела
     * @param salary Голый оклад по отделу
     * @return Добавляет новый отдел и возвращает краткий отчет по новой записи
     */
    Department addDepartment(String name, String salary) throws InputArgsErrorException, DepartmentAlreadyAddedException;

    Department getDepartment(Integer departmentID);

    String maxSalary(int depID);

    String minSalary(int depID);

    String allEmplByDep();
}
