package com.arcad.employeebook.controller;

import com.arcad.employeebook.exception.DepartmentAlreadyAddedException;
import com.arcad.employeebook.exception.InputArgsErrorException;
import com.arcad.employeebook.service.api.DepartmentServiceImpl;
import com.arcad.employeebook.service.api.EmployeeBookUtilite;
import com.arcad.employeebook.view.ViewService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/Department")
public class DepartmentController {

    private final ViewService viewService;
    private final DepartmentServiceImpl departmentService;
    private final EmployeeBookUtilite employeeBookUtilite;

    public DepartmentController(ViewService viewService, DepartmentServiceImpl departmentService, EmployeeBookUtilite employeeBookUtilite) {
        this.viewService = viewService;
        this.departmentService = departmentService;
        this.employeeBookUtilite = employeeBookUtilite;
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
        String result;
        result = String.valueOf(departmentService.
                    allEmplByDep());
        return viewService.viewOut("Список сотрудников по отделам", result);
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
            result = String.valueOf(departmentService.
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
            throws InputArgsErrorException, DepartmentAlreadyAddedException {
        String result;
        List<String> argsString = new java.util.ArrayList<>(List.of(name));
        List<String> argsNumb = List.of(salary);
        if (employeeBookUtilite.isReqParamString(argsString) &
                employeeBookUtilite.isReqParamNum(argsNumb)) {
            argsString.addAll(argsNumb);
            result = String.valueOf(departmentService.addDepartment(name, salary));    //Список сотрудников
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
        String result;
        result = departmentService.maxSalary(Integer.parseInt(depID));
        return viewService.viewOut("Максимальная зарплата", result);

    }

    /**
     * @param depID
     * @return Возвращать сотрудника с минимальной зарплатой
     * на основе номера отдела.
     */
    @GetMapping(path = "/min-salary/{depID}")
    public String minSalary(@PathVariable String depID) {
        String result;
        result = departmentService.minSalary(Integer.parseInt(depID));
        return viewService.viewOut("Минимальная зарплата", result);

    }
}
