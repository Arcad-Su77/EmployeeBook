package com.arcad.employeebook.service.api;

import com.arcad.employeebook.elementaryClasses.Department;
import com.arcad.employeebook.elementaryClasses.Employee;
import com.arcad.employeebook.service.exception.EmployeeAlreadyAddedException;
import com.arcad.employeebook.service.impl.DepartmentService;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

//@Service
@Repository
public class DepartmentServiceImpl implements DepartmentService {
    private Map<Integer, Department> departments;
    private final EmployeeServiceImpl employeService;


    public DepartmentServiceImpl( EmployeeServiceImpl employeService) {
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
    public List<Department> printAllDepartment(String idd) {
        String result="<tr><th>ID</th><th>Оклад отдела</th><th>Наименование отдела</th></tr>";
        if (idd.equals("0")) {
            for (Map.Entry<Integer, Department> depMap : departments.entrySet()) {
                if (depMap != null) {
                    result += "<tr><td>" + depMap.getValue().getDepartmentID() +
                            "</td><td>" + depMap.getValue().getSalary() +
                            "</td><td>" + depMap.getValue().getName() + "</td></tr>";
                }
            }
        } else {
            result += employeService.employeeByIDDep(idd);
            }

        return result;
    }

    /**
     * @param name
     * @param salary
     * @return
     */
    @Override
    public String addDepartment(String name, String salary) {
        String result="<tr><th>ID</th><th>Оклад отдела</th><th>Наименование отдела</th></tr>";
        Double doSalary = Double.valueOf(salary);

        if (name.isEmpty() && (salary.isEmpty() || doSalary.isNaN())) {
            result += "<tr><td>" + "Параметры не верные" +
                    "</td><td>" + salary +
                    "</td><td>" + name + "</td></tr>";
        } else {
            Department addNew = new Department(name, doSalary);
            Department depNew = departments.putIfAbsent(addNew.hashCode(), addNew);
            if (depNew == null) {
                result += "<tr><td>" + addNew.getDepartmentID() +
                                "</td><td>" + addNew.getSalary() +
                                "</td><td>" + addNew.getName() + "</td></tr>";
            } else {
                Department.decCount();
                throw new EmployeeAlreadyAddedException("Такой отдел уже добавлен!!! \n" + addNew);
            }
        }
        return result;
    }

    @Override
    public Department getDepartment(Integer departmentID) {
        return departments.values().stream()
                .filter(department -> (department.getDepartmentID() == departmentID))
                .findFirst().get();
    }

    @Override
    public String maxSalary(String depID) {
        List<Employee> empOfDep = employeService.employeeByIDDep(depID);
        double maxSalary = empOfDep.stream()
                .mapToDouble(Employee::getSalary)
                .max()
                .orElse(0);
        return departments.get(depID).getName() + ": " + maxSalary;
    }

}
