package com.arcad.employeebook.controller;

import com.arcad.employeebook.elementaryClasses.Employee;
import com.arcad.employeebook.exception.DepartmentAlreadyAddedException;
import com.arcad.employeebook.exception.InputArgsErrorException;
import com.arcad.employeebook.service.api.DepartmentServiceImpl;
import com.arcad.employeebook.service.api.EmployeeBookService;
import com.arcad.employeebook.service.api.EmployeeBookUtilite;
import com.arcad.employeebook.service.impl.DepartmentService;
import com.arcad.employeebook.view.ViewService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final ViewService viewService;
    private final EmployeeBookService employeeBookService;
    private final EmployeeBookUtilite employeeBookUtilite;
    private final DepartmentService departmentService;

    public DepartmentController(ViewService viewService,
                                EmployeeBookService employeeBookService,
                                EmployeeBookUtilite employeeBookUtilite,
                                DepartmentService departmentService) {
        this.viewService = viewService;
        this.employeeBookService = employeeBookService;
        this.employeeBookUtilite = employeeBookUtilite;
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/all")
    public String printAllDepartment() {
        String result = String.valueOf(departmentService.allDepartment());    //Список сотрудников
        return viewService.viewOutTable("Список отделов", result);
    }
    /**
     * @return Возвращать всех сотрудников по всем отделам.
     */
    @GetMapping(path = "/all/employee")
    public String printEmplByDep() {
        Map<Integer, List<Employee>> allEmplByDep = new HashMap<>();
        allEmplByDep = employeeBookService.allEmplByDep();
        return viewService.viewOut("Список сотрудников по отделам", allEmplByDep.toString());
    }
    /**
     * @param idd
     * @return Возвращать всех сотрудников по отделу.
     */
    @GetMapping(path = "/all/{idd}/employee")
    public String printEmplByDepID(@PathVariable String idd) {
        String result;
        EmployeeBookUtilite ebUtilite = new EmployeeBookUtilite();
        if (ebUtilite.isReqParamNum(Collections.singletonList(idd))) {
            result = String.valueOf(employeeBookService.
                    employeeByDepartment(Integer.parseInt(idd)));
        } else {
            result = "Двнные не вернве";
        }
        return viewService.viewOutTable("Список отделов", result);
    }

    /**
     * @param name
     * @param salary
     * @return Добавление нового департамента.
     * @throws InputArgsErrorException
     * @throws DepartmentAlreadyAddedException
     */
    @GetMapping(path = "/add")
    public String addDepartment(@RequestParam("name") String name,
                                @RequestParam("salary") String salary)
            throws DepartmentAlreadyAddedException {
        String result;
        List<String> argsString = new java.util.ArrayList<>(List.of(name));
        List<String> argsNumb = List.of(salary);
        if (employeeBookUtilite.isReqParamString(argsString) &
                employeeBookUtilite.isReqParamNum(argsNumb)) {
            argsString.addAll(argsNumb);
            result = String.valueOf(departmentService.addDepartment(name, Integer.valueOf(salary)));    //Список сотрудников
        } else {
            argsString.addAll(argsNumb);
            result = "<tr><td> Введены не верные данные </td></tr>" +
                    "<tr><td>" + argsString + "</td></tr>";
        }
        return viewService.viewOutTable("Добавляем отдел", result);
    }

    /**
     * @param depID
     * @return Возвращать сотрудника с максимальной зарплатой
     * на основе номера отдела.
     */
    @GetMapping(path = "/max-salary/{depID}")
    public String maxSalary(@PathVariable String depID) {
        Employee result;
        result = employeeBookService.maxSalary(Integer.parseInt(depID));
        return viewService.viewOut("Максимальная зарплата",
                employeeBookService.employeeToString(result));

    }

    /**
     * @param depID
     * @return Возвращать сотрудника с минимальной зарплатой
     * на основе номера отдела.
     */
    @GetMapping(path = "/min-salary/{depID}")
    public String minSalary(@PathVariable String depID) {
        Employee result;
        result = employeeBookService.minSalary(Integer.parseInt(depID));
        return viewService.viewOut("Минимальная зарплата",
                employeeBookService.employeeToString(result));

    }
}
