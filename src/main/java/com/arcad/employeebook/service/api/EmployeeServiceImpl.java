package com.arcad.employeebook.service.api;

import com.arcad.employeebook.elementaryClasses.Employee;
import com.arcad.employeebook.exception.EmployeeAlreadyAddedException;
import com.arcad.employeebook.service.impl.EmployeeService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@Service
@Repository
public class EmployeeServiceImpl implements EmployeeService {
    private Map<Integer, Employee> employees;

    public EmployeeServiceImpl() {

    }

    public EmployeeServiceImpl(Map<Integer, Employee> emp) {
        this.employees = emp;
    }


    // Реализуем метод printAllEmployee (распечатать всех сотрудников)
    @Override
    public List<Employee> EmployeeAll() {
        return new ArrayList<>(employees.values());
    }

    public Employee getEmployee(Integer empID) {
        return employees.get(empID);
    }
    @Override
    public String addEmploeey(List<String> args) throws EmployeeAlreadyAddedException {
        String result = "<tr><th>Добавляем сотрудника с параметрами</th></tr>";
        Employee addNew = new Employee(args.get(0), args.get(1), args.get(2), Integer.parseInt(args.get(3)), Float.parseFloat(args.get(4)));
        Employee emplNew = employees.putIfAbsent(addNew.hashCode(), addNew);
        if (emplNew == null){
           return result + "<tr><td>" + args + "</td></tr>" +
                    "<tr><td>" + addNew + "</td></tr>";
        } else {
            Employee.decCount();
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже добавлен!!! \n" + emplNew);
        }
    }

    @Override
    public List<Employee> employeeByIDDep(int idd) {
        return employees.values().stream()
                .filter(employee -> ((employee.getDepartmentID() == idd) || (idd == 0)))
                .collect(Collectors.toList());
    }

    @Override
    public Employee editEmployee(Integer inID,
                                 String firstName, String lastName,
                                 String midleName, Float scaleRatio) {
        if (firstName != null) employees.get(inID).setFirstName(firstName);
        if (lastName != null) employees.get(inID).setLastName(lastName);
        if (midleName != null) employees.get(inID).setMidleName(midleName);
        if (scaleRatio != null) employees.get(inID).setScaleRatio(scaleRatio);
        return employees.get(inID);
    }
    @Override
    public Employee editEmployee(Integer inID,
                                 String firstName, String lastName, String midleName){
        return editEmployee(inID,firstName, lastName, midleName,null);
    }
    @Override
    public Employee editEmployee(Integer inID, Float scaleRatio){
        return editEmployee(inID, null, null, null, scaleRatio);
    }

}
