package com.arcad.employeebook.service.api;

import com.arcad.employeebook.elementaryClasses.Department;
import com.arcad.employeebook.elementaryClasses.Employee;
import com.arcad.employeebook.exception.DepartmentAlreadyAddedException;
import com.arcad.employeebook.exception.InputArgsErrorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
    @InjectMocks
    private DepartmentServiceImpl departmentService;
    @Mock
    EmployeeServiceImpl employeeService;
    @InjectMocks
    EmployeeBookService employeeBookService;
    Map<Integer, Department> expectedDepartments;
    private List<Employee> employeeList;
    private List<Department> departmentList;

    @BeforeEach
    public void setUP() throws InputArgsErrorException, DepartmentAlreadyAddedException {
        departmentService = new DepartmentServiceImpl();

        Map<Integer, Employee> expectedEmployees = new HashMap<>();
        Employee emp = new Employee("John", "Alex", "M", 1, 2.0f);
        expectedEmployees.put(emp.getEmployeeID(), emp);
        emp = new Employee("Jane", "Max", "L", 2, 2.2f);
        expectedEmployees.put(emp.getEmployeeID(), emp);
        emp = new Employee("Jane2", "Max2", "L", 2, 2.0f);
        expectedEmployees.put(emp.getEmployeeID(), emp);
        emp = new Employee("John", "Vlad", "M", 3, 1.8f);
        expectedEmployees.put(emp.getEmployeeID(), emp);
        emp = new Employee("Jane", "Den", "L", 4, 1.6f);
        expectedEmployees.put(emp.getEmployeeID(), emp);
        employeeList = new ArrayList<>(expectedEmployees.values());
        Department dept = new Department("Uchred", 5000.0);
        departmentService.addDepartment(dept);
        departmentService.addDepartment("AUP", 4500);
        departmentService.addDepartment("IT", 4000);
        departmentService.addDepartment("Logist", 3500);

        Department dep = new Department("Sales", 1200);
        Department dep2 = new Department("Finance", 1500);

        expectedDepartments = new HashMap<>();
        expectedDepartments.put(dep.getDepartmentID(), dep);
        expectedDepartments.put(dep2.getDepartmentID(), dep2);
    }
    
    @Test
    public void testGetDepartmentsMethod() {
        departmentService.setDepartments(expectedDepartments);
        // Act
        Map<Integer, Department> actualDepartments = departmentService.getDepartments();
        // Assert
        Assertions.assertEquals(expectedDepartments, actualDepartments);
    }
    @Test
    public void testAddDepartment_WithValidArguments_ReturnsNewDepartment() throws DepartmentAlreadyAddedException {
        // Arrange
        String departmentName = "Engineering";
        double salary = 70000;
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
        String departmentName = "Marketing";
        int salary = 60000;
        departmentService.addDepartment(departmentName, salary);
        // Act & Assert
        assertThrows(DepartmentAlreadyAddedException.class,
                () -> departmentService.addDepartment(departmentName, salary));
    }

    @Test
    public void testAddDepartment_WithNullArguments_ThrowsException() {
        // Act & Assert
        assertThrows(InputArgsErrorException.class,
                () -> departmentService.addDepartment(null));
    }

    @Test
    public void testAddDepartment_WithExistingDepartmentObject_ThrowsException() throws DepartmentAlreadyAddedException, InputArgsErrorException {
        // Arrange
        Department department = new Department("Sales", 50000);
        departmentService.addDepartment(department);
        // Act & Assert
        assertThrows(DepartmentAlreadyAddedException.class,
                () -> departmentService.addDepartment(department));
    }

    @Test
    public void testEditDepartment_WithValidArguments_UpdatesDepartment() {
        int departmentID = 1;
        String newName = "Sovet directorov";
        double newSalary = 10000.0;
        assertDoesNotThrow(() -> departmentService.editDepartment(departmentID, newName, newSalary));
        Department updatedDepartment = departmentService.getDepartments().get(departmentID);
        Assertions.assertEquals(newName, updatedDepartment.getName());
        Assertions.assertEquals(newSalary, updatedDepartment.getSalary());
    }

    @Test
    public void testAllDepartment() {
        departmentService.setDepartments(expectedDepartments);
        List<Department> expected = expectedDepartments.values().stream().toList();
        List<Department> actualDepartments = departmentService.allDepartment();
        Assertions.assertEquals(expected, actualDepartments);
    }

    @Test
    public void testEditDepartment_WithInvalidDepartmentID_DoesNotUpdateDepartment() {
        int departmentID = 10;
        String newName = "New Department";
        double newSalary = 10000.0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> departmentService.editDepartment(departmentID, newName, newSalary));
        Department updatedDepartment = departmentService.getDepartments().get(departmentID);
        Assertions.assertNull(updatedDepartment);
    }

}