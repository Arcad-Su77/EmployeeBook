package com.arcad.employeebook.service.api;

import com.arcad.employeebook.elementaryClasses.Department;
import com.arcad.employeebook.exception.DepartmentAlreadyAddedException;
import com.arcad.employeebook.exception.InputArgsErrorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DepartmentServiceImplTests {
    @Test
    public void testAddDepartment_WithValidArguments_ReturnsNewDepartment() throws DepartmentAlreadyAddedException {
        // Arrange
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        String departmentName = "Engineering";
        double salary = 70000d;
        
        // Act
        Department department = departmentService.addDepartment(departmentName, salary);
  
        // Assert
        Assertions.assertNotNull(department, "Check the department is not null");
        Assertions.assertEquals(departmentName, department.getName());
        Assertions.assertEquals(salary, department.getSalary());
    }

    @Test
    public void testAddDepartment_DepartmentAlreadyExists_ThrowsException() throws DepartmentAlreadyAddedException {
        // Arrange
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        String departmentName = "Marketing";
        double salary = 60000;
        departmentService.addDepartment(departmentName, salary);
        
        // Act & Assert
        Assertions.assertThrows(DepartmentAlreadyAddedException.class, 
            () -> departmentService.addDepartment(departmentName, salary));
    }
    
    @Test
    public void testAddDepartment_WithNullArguments_ThrowsException() {
        // Arrange
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        
        // Act & Assert
        Assertions.assertThrows(InputArgsErrorException.class, () -> departmentService.addDepartment(null));
    }
    
    @Test
    public void testAddDepartment_WithExistingDepartmentObject_ThrowsException() throws DepartmentAlreadyAddedException, InputArgsErrorException {
        // Arrange
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        Department department = new Department("Sales", 50000);
        departmentService.addDepartment(department);
        
        // Act & Assert
        Assertions.assertThrows(DepartmentAlreadyAddedException.class, () -> departmentService.addDepartment(department));
    }
}