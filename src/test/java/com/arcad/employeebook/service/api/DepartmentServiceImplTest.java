package com.arcad.employeebook.service.api;

import com.arcad.employeebook.elementaryClasses.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.HashMap;
import java.util.Map;


@SpringBootTest
public class DepartmentServiceImplTest {
    
    @Test
    public void testGetDepartmentsMethod() {
        
        // Arrange 
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();

        Department dep = new Department("Sales", 1200);
        Department dep2 = new Department("Finance", 1500);
        
        Map<Integer, Department> expectedDepartments = new HashMap<>();
        expectedDepartments.put(dep.getDepartmentID(), dep);
        expectedDepartments.put(dep2.getDepartmentID(), dep2);
        departmentService.setDepartments(expectedDepartments);
        
        // Act 
        Map<Integer, Department> actualDepartments = departmentService.getDepartments();
        
        // Assert 
        Assertions.assertEquals(expectedDepartments, actualDepartments);
    }
}