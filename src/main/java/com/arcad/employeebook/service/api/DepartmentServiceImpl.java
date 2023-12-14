package com.arcad.employeebook.service.api;

import com.arcad.employeebook.elementaryClasses.Department;
import com.arcad.employeebook.elementaryClasses.Employee;
import com.arcad.employeebook.exception.DepartmentAlreadyAddedException;
import com.arcad.employeebook.exception.InputArgsErrorException;
import com.arcad.employeebook.service.impl.DepartmentService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
@Repository
public class DepartmentServiceImpl implements DepartmentService {
    private Map<Integer, Department> departments;
    private final EmployeeServiceImpl employeService;


    public DepartmentServiceImpl(EmployeeServiceImpl employeService) {
        this.employeService = employeService;
    }

    public DepartmentServiceImpl(Map<Integer, Department> dep, EmployeeServiceImpl employeService) {
        this.departments = dep;
        this.employeService = employeService;
    }

    public Map<Integer, Department> getDepartments() {
        return departments;
    }

    @Override
    public List<Department> allDepartment() {
        return departments.values().stream()
                .toList();
    }

    /**
     * @param idd - номер департамента
     * @return Возвращать всех сотрудников по отделу.
     */
    @Override
    public List<Employee> employeeByDepartment(int idd) {
        return employeService.employeeByIDDep(idd);
    }

    /**
     * @param name
     * @param salary
     * @return Добавление нового департамента.
     */
    @Override
    public Department addDepartment(String name, String salary)
            throws InputArgsErrorException, DepartmentAlreadyAddedException {
        Department result;
        Double doSalary = Double.valueOf(salary);

        if (name.isEmpty() && (salary.isEmpty() || doSalary.isNaN()))
            throw new InputArgsErrorException("Входные данные для добавления нового департамента не верные");
        else {
            Department addNew = new Department(name, doSalary);
            Department depNew = departments.putIfAbsent(addNew.getDepartmentID(), addNew);
            if (depNew == null) {
                result = addNew;
            } else {
                Department.decCount();
                throw new DepartmentAlreadyAddedException("Такой отдел уже добавлен!!! \n" + addNew);
            }
        }
        return result;
    }

    /**
     * @param departmentID
     * @return Возвращается весь департамент
     * на основе его номера
     */
    @Override
    public Department getDepartment(Integer departmentID) {
        return departments.values().stream()
                .filter(department -> (department.getDepartmentID() == departmentID))
                .findFirst().get();
    }

    /**
     * @param depID
     * @return Возвращать сотрудника с максимальной зарплатой
     * на основе номера отдела.
     */
    @Override
    public String maxSalary(int depID) {
        List<Employee> empOfDep = employeService.employeeByIDDep(depID);
        Employee empMaxSalary = empOfDep.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null);
        return departments.get(depID).getName() + ": "
                + empMaxSalary.getEmployeeFIO() + " с максимальной зарплатой: "
                + empMaxSalary.getSalary();
    }

    /**
     * @param depID
     * @return Возвращать сотрудника с минимальной зарплатой
     * на основе номера отдела.
     */
    @Override
    public String minSalary(int depID) {
        List<Employee> empOfDep = employeService.employeeByIDDep(depID);
        Employee empMinSalary = empOfDep.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null);
        return departments.get(depID).getName() + ": "
                + empMinSalary.getEmployeeFIO() + " с минимальной зарплатой: "
                + empMinSalary.getSalary();
    }

    /**
     * @return Возвращать всех сотрудников по всем отделам.
     */
    @Override
    public String allEmplByDep() {
        List<Department> deps = allDepartment();
        StringBuilder result = new StringBuilder();
        for (Department dep : deps) {
            List<Employee> emps = employeService.employeeByIDDep(dep.getDepartmentID());
            emps.sort(Comparator.comparing(Employee::getLastName));
            result.append(dep.getName()).append(": ");
            for (Employee emp : emps) {
                result.append(emp.getEmployeeFIO()).append(", ");
            }
            result.delete(result.length() - 2, result.length());
            result.append("\n");
        }
        return result.toString();
    }

}
