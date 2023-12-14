package com.arcad.employeebook.service.api;

import com.arcad.employeebook.elementaryClasses.Department;
import com.arcad.employeebook.elementaryClasses.Employee;
import com.arcad.employeebook.exception.DepartmentAlreadyAddedException;
import com.arcad.employeebook.exception.InputArgsErrorException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class DepartmentServiceImplTest {

    @InjectMocks
    DepartmentServiceImpl departmentService;

    @MockBean
    EmployeeServiceImpl employeeService;
    private List<Employee> employees;

    @BeforeEach
    public void setUp() throws InputArgsErrorException, DepartmentAlreadyAddedException {
        departmentService = new DepartmentServiceImpl(employeeService);
        employees = new ArrayList<>();
        employees.add(new Employee("TestEmployee1",
                "TestPosition1", "midNam1",
                1, 1.5F));
        employees.add(new Employee("TestEmployee2",
                "TestPosition2", "midNam2",
                1, 1.7F));

        departmentService.addDepartment("AUP", "5000");
        departmentService.addDepartment("IT-bak","4000");


    }

    @Test
    public void testMaxSalary() {
        EmployeeServiceImpl employeeService = Mockito.mock(EmployeeServiceImpl.class);
        Map<Integer, Department> mockedDepartments = new HashMap<>();
        Department dept = new Department("Dep1", 5000d);
        mockedDepartments.put(dept.hashCode(), dept);
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(mockedDepartments, employeeService);



        when(employeeService.employeeByIDDep(1)).thenReturn(employees);

        String expected = dept.getName() + ": " + (5000d*1.7);
        String actual = departmentService.maxSalary(1);

        assertEquals(expected, actual);
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

        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departments, employeeService);


        when(employeeService.employeeByIDDep(1))
                .thenReturn(employees);

        String expected = department.getName() + ": " + 6500.0;
        String actual = departmentService.minSalary(department.getDepartmentID());

        assertEquals(expected, actual, "Minimal Salary calculation failed for valid Department ID");
    }

    /**
     * Description:
     * Given an invalid Department ID, an exception is thrown.
     */
    @Test
    void testMinSalary_withInvalidDepartment() {
        Map<Integer, Department> departments = new HashMap<>();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departments, employeeService);

        when(employeeService.employeeByIDDep(1)).thenReturn(Collections.emptyList());

        assertThrows(NullPointerException.class, () -> departmentService.minSalary(1),
                "Не выдано исключение для неверного идентификатора отдела.");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getDepartments() {
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
}