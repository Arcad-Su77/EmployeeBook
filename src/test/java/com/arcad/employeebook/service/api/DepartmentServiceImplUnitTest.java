package com.arcad.employeebook.service.api;

import com.arcad.employeebook.elementaryClasses.Department;
import com.arcad.employeebook.elementaryClasses.Employee;
import com.arcad.employeebook.exception.DepartmentAlreadyAddedException;
import com.arcad.employeebook.service.impl.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DepartmentServiceImplUnitTest {


    private DepartmentService departmentService;

    @BeforeEach
    public void setUp() throws DepartmentAlreadyAddedException {
        departmentService = new DepartmentServiceImpl();
        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("TestEmployee1",
                "TestPosition1", "midNam1",
                1, 1.5F));
        employeeList.add(new Employee("TestEmployee2",
                "TestPosition2", "midNam2",
                1, 1.7F));

        departmentService.addDepartment("AUP", 5000);
        departmentService.addDepartment("IT-bak",4000);
    }
    @Test
    void testAddDepartment() throws DepartmentAlreadyAddedException {
        // Arrange
        Map<Integer, Department> departmentsMap = new HashMap<>();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        departmentService.setDepartments(departmentsMap);
        Department newDepartment = new Department("Testing", 50000);

        // Act
        Department addedDepartment = departmentService.addDepartment("Testing", 50000);

        // Assert
        assertEquals(newDepartment, addedDepartment);
    }

    @Test
    void testAddDepartmentWithSameDetails() throws DepartmentAlreadyAddedException {
        // Arrange
        departmentService.addDepartment("Testing", 50000);

        // Act & Assert
        assertThrows(DepartmentAlreadyAddedException.class, () ->
                departmentService.addDepartment("Testing", 50000));
    }

}