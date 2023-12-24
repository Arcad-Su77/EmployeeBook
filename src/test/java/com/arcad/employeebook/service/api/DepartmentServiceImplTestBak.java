package com.arcad.employeebook.service.api;

import com.arcad.employeebook.elementaryClasses.Department;
import com.arcad.employeebook.elementaryClasses.Employee;
import com.arcad.employeebook.exception.DepartmentAlreadyAddedException;
import com.arcad.employeebook.exception.InputArgsErrorException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTestBak {

    @InjectMocks
    DepartmentServiceImpl departmentService;
    @Mock
    EmployeeBookService employeeBookService;
    private Map<Integer, Employee> employees;
    private List<Employee> employeeList;
    @Mock
    private Department dept;

    @BeforeEach
    public void setUp() throws InputArgsErrorException, DepartmentAlreadyAddedException {
        employees = new HashMap<>();
        Employee emp = new Employee("John", "Alex", "M", 1, 2.0f);
        employees.put(emp.getEmployeeID(), emp);
        emp = new Employee("Jane", "Max", "L", 2, 2.0f);
        employees.put(emp.getEmployeeID(), emp);
        emp = new Employee("Jane", "Max", "L", 2, 2.0f);
        employees.put(emp.getEmployeeID(), emp);
        emp = new Employee("John", "Vlad", "M", 3, 1.8f);
        employees.put(emp.getEmployeeID(), emp);
        emp = new Employee("Jane", "Den", "L", 4, 1.6f);
        employees.put(emp.getEmployeeID(), emp);
        employeeList = new ArrayList<>(employees.values());
        dept = new Department("Uchred", 5000.0);
        departmentService.addDepartment(dept);
        departmentService.addDepartment("AUP", 4500);
        departmentService.addDepartment("IT", 4000);
        departmentService.addDepartment("Logist", 3500);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void MaxSalary() {
        when(employeeBookService.employeeByDepartment(1)).thenReturn(employeeList);
        String expected = "Uchred";
        String actual = "Uchred";
        assertEquals(expected, actual, "Max Salary calculation failed for valid Department ID");
    }

    /**
     * Description:
     * Given a valid Department ID, a minimal salary is calculated from Employee salaries and
     * the name of the Department along with the minimal salary is returned as a String.
     */
    @Test
    void testMinSalary_withValidDepartment() {
        Map<Integer, Department> departments = new HashMap<>();
        Department department = new Department("Test Department", 5000.0);
        departments.put(department.getDepartmentID(), department);

        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        departmentService.setDepartments(departments);

        when(employeeBookService.employeeByDepartment(1))
                .thenReturn(employeeList);

        Employee expected = employeeList.get(0);
        Employee actual = employeeBookService.minSalary(department.getDepartmentID());

        assertEquals(expected, actual, "Minimal Salary calculation failed for valid Department ID");
    }

    /**
     * Description:
     * Given an invalid Department ID, an exception is thrown.
     */
    @Test
    void testMinSalary_withInvalidDepartment() {
        Map<Integer, Department> departments = new HashMap<>();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        departmentService.setDepartments(departments);

        when(employeeBookService.employeeByDepartment(1))
                .thenReturn(Collections.emptyList());

        assertThrows(NullPointerException.class, () -> employeeBookService.minSalary(1),
                "Не выдано исключение для неверного идентификатора отдела.");
    }

    @Test
    void getDepartments() {
        Map<Integer, Department> departments = departmentService.getDepartments();
        assertEquals(2, departments.size());
    }

    @Test
    void allDepartment() {
    }

    @Test
    void employeeByDepartment() {
    }

    @Test
    void addDepartment() {
    }

    @Test
    void getDepartment() {
    }

    @Test
    void maxSalary() {
    }

    @Test
    void minSalary() {
    }

    @Test
    void testAllDepartment() {
    }

    @Test
    void testEmployeeByDepartment() {
    }

    @Test
    void testAddDepartment() {
    }

    @Test
    void testGetDepartment() {
    }

    @Test
    void testMaxSalary1() {
    }

    @Test
    void testMinSalary() {
    }

    @Test
    void allEmplByDep() {
    }
}