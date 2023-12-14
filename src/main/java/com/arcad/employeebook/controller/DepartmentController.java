package com.arcad.employeebook.controller;

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

    @GetMapping(path = "/printAll")
//    @RequestParam("num1") String num1, @RequestParam("num2") String num2
    public String printAllDepartment() {
        String result = departmentService.printAllDepartment("0");    //Список сотрудников
        return viewService.viewOutTable("Список отделов", result);
    }
    @GetMapping(path = "/printAll/{idd}/employee")
//    @RequestParam("num1") String num1, @RequestParam("num2") String num2
    public String printDepartmentID(@PathVariable String idd) {
        String result;
        EmployeeBookUtilite ebUtilite = new EmployeeBookUtilite();
        if (ebUtilite.isReqParamNum(Collections.singletonList(idd))) {
            result = departmentService.printAllDepartment(idd);
        } else {
            result = "Двнные не вернве";
        }
        return viewService.viewOutTable("Список отделов", result);
    }

    @GetMapping(path = "/add")
    public String addDepartment(@RequestParam("name") String name,
                                @RequestParam("salary") String salary) {
        String result;
        List<String> argsString = new java.util.ArrayList<>(List.of(name));
        List<String> argsNumb = List.of(salary);
        if (employeeBookUtilite.isReqParamString(argsString) &
                employeeBookUtilite.isReqParamNum(argsNumb)) {
            argsString.addAll(argsNumb);
            result = departmentService.addDepartment(name, salary);    //Список сотрудников
        } else {
            argsString.addAll(argsNumb);
            result = "<tr><td> Введены не верные данные </td></tr>" +
                    "<tr><td>" + argsString + "</td></tr>";
        }
        return viewService.viewOutTable("Добавляем отдел", result);
    }

    @GetMapping(path = "/max-salary/{depID}")
//    @RequestParam("num1") String num1, @RequestParam("num2") String num2
    public String maxSalary(@PathVariable String depID) {
        String result;
        result = departmentService.maxSalary(depID);

    }
}
