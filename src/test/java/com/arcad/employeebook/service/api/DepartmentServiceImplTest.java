package com.arcad.employeebook.service.api;

import com.arcad.employeebook.elementaryClasses.Department;
import com.arcad.employeebook.elementaryClasses.Employee;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DepartmentServiceImplTest {

    @Test
    public void testMaxSalary() {
        EmployeeServiceImpl employeeService = Mockito.mock(EmployeeServiceImpl.class);
        Map<Integer, Department> mockedDepartments = new HashMap<>();
        Department dept = new Department("Dep1", 5000d);
        mockedDepartments.put(dept.hashCode(), dept);
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(mockedDepartments, employeeService);

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("TestEmployee1", "TestPosition1", "midNam1",1, 1.5));
        employees.add(new Employee("TestEmployee2", "TestPosition2", "midNam2",1, 1.7));

        when(employeeService.employeeByIDDep(1).thenReturn(employees);

        String expected = dept.getName() + ": " + (5000d*1.7);
        String actual = departmentService.maxSalary(employeeService.employeeByIDDep(1));

        assertEquals(expected, actual);
    }
}