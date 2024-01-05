package com.arcad.employeebook.service.api;

import com.arcad.employeebook.elementaryClasses.Department;
import com.arcad.employeebook.elementaryClasses.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;

@Service
public class EmployeeBookService {
    private double miniSalary;
    private double midleSalary;
    private double maxiSalary;
    private EmployeeServiceImpl employeeService;
    private DepartmentServiceImpl departmentService;

    public EmployeeBookService() {
    }

    @Autowired
    public EmployeeBookService(EmployeeServiceImpl employeeService, DepartmentServiceImpl departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    public double getMiniSalary() {
        return miniSalary;
    }
    public double getMidleSalary() {
        return midleSalary;
    }
    public double getMaxiSalary() {
        return maxiSalary;
    }
    public void updateSalary() {
        upMiniSalary();
//        upMidleSalary();
        upMaxiSalary();
    }

    private void upMaxiSalary() {
        maxiSalary = getSalary(maxSalary(0));
    }

//    private void upMidleSalary() {
//        midleSalary = getSalary(findEmployeeMidleSalary());
//    }

    private void upMiniSalary() {
        miniSalary = getSalary(minSalary(0));
    }

    public double getSalary(Employee emp){
        double scRatio = employeeService.getEmployee(emp.getEmployeeID()).getScaleRatio();
        double salary = departmentService.getDepartment(emp.getDepartmentID()).getSalary();
        return salary * scRatio;
    }
    public double getSumSalary() {
        return employeeService.EmployeeAll().stream()
                .mapToDouble(this::getSalary)
                .reduce(0.0, Double::sum);
    }

    /**
     * @param idd - номер департамента
     * @return Возвращать всех сотрудников по отделу.
     */
    public List<Employee> employeeByDepartment(int idd) {
        return employeeService.employeeByIDDep(idd);
    }

    /**
     * @param depID
     * @return Возвращать сотрудника с максимальной зарплатой
     * на основе номера отдела.
     */
    public Employee maxSalary(int depID) {
        return employeeByDepartment(depID).stream()
                .max(comparing(this::getSalary))
                .orElse(null);
    }

    /**
     * @param depID
     * @return Возвращать сотрудника с минимальной зарплатой
     * на основе номера отдела.
     */
    public Employee minSalary(int depID) {
        return employeeByDepartment(depID).stream()
                .min(comparing(this::getSalary))
                .orElse(null);
    }

    /**
     * @return Возвращать всех сотрудников по всем отделам.
     */
    public Map<Integer, List<Employee>> allEmplByDep() {
        Map<Integer, List<Employee>> allEmployeesByDepartment = new HashMap<>();
        for (Department department : departmentService.allDepartment()) {
            List<Employee> employees = employeeService.employeeByIDDep(department.getDepartmentID());
            allEmployeesByDepartment.put(department.getDepartmentID(), employees);
        }
        return allEmployeesByDepartment;
    }
    public String employeeToString(Employee emp) {
        return emp.getEmployeeShortFIO() + ", Отдел:" + departmentService.getDepartment(emp.getDepartmentIndexID()).getName() + ", Зарплата: " + getSalary(emp);
    }

}
