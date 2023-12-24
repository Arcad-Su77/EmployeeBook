package com.arcad.employeebook.service.impl;

import com.arcad.employeebook.elementaryClasses.Department;
import com.arcad.employeebook.elementaryClasses.Employee;
import com.arcad.employeebook.exception.DepartmentAlreadyAddedException;
import com.arcad.employeebook.exception.InputArgsErrorException;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    List<Department> allDepartment();

    /**
     * @param name   Название отдела
     * @param salary Голый оклад по отделу
     * @return Добавляет новый отдел и возвращает краткий отчет по новой записи
     */

    Department addDepartment(String name, Integer salary)
            throws DepartmentAlreadyAddedException;

    Department getDepartment(Integer departmentID);

    void editDepartment(Integer inID, String name, Integer salary);

    void editDepartment(Integer inID, String name);

    void editDepartment(Integer inID, Integer salary);
}
