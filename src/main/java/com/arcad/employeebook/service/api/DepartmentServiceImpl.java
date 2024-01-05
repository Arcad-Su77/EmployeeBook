package com.arcad.employeebook.service.api;

import com.arcad.employeebook.elementaryClasses.Department;
import com.arcad.employeebook.exception.DepartmentAlreadyAddedException;
import com.arcad.employeebook.exception.InputArgsErrorException;
import com.arcad.employeebook.service.impl.DepartmentService;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DepartmentServiceImpl implements DepartmentService {
    private Map<Integer, Department> departments;

    public DepartmentServiceImpl() {
        this.departments = new HashMap<>();
    }

    public void setDepartments(Map<Integer, Department> departments) {
        this.departments = departments;
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
     * @param name Название отдела
     * @param salary Голый оклад
     * @return Добавление нового департамента.
     */
    @Override
    public Department addDepartment(String name, double salary) throws DepartmentAlreadyAddedException {
        Department result;
        Department addNew = new Department(name, salary);
        if (!departments.containsValue(addNew)) {
            departments.putIfAbsent(addNew.getDepartmentID(), addNew);
            result = addNew;
        } else {
            Department.decCount();
            throw new DepartmentAlreadyAddedException("Такой отдел уже добавлен!!! \n" + addNew);
        }
        return result;
    }

    public Department addDepartment(Department addNew) throws InputArgsErrorException, DepartmentAlreadyAddedException {
        Department result;
        if (addNew == null)
            throw new InputArgsErrorException("Департамент для добавления нового департамента не верные");
        else {
            Department depNew;
            depNew = departments.putIfAbsent(addNew.getDepartmentID(), addNew);
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
    @Override
    public void editDepartment(Integer inID,String name,Double salary) {
        if (!departments.containsKey(inID)) {
            throw new IllegalArgumentException("Department with ID " + inID + " does not exist");
        }
        if (name != null) departments.get(inID).setName(name);
        if (salary != null) departments.get(inID).setSalary(salary);
    }
    @Override
    public void editDepartment(Integer inID,String name){
        editDepartment(inID,name,null);
    }
    @Override
    public void editDepartment(Integer inID,double salary){
        editDepartment(inID,null,salary);
    }

}
