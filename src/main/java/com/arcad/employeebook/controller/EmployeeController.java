package com.arcad.employeebook.controller;

import com.arcad.employeebook.elementaryClasses.Employee;
import com.arcad.employeebook.service.api.EmployeeBookUtilite;
import com.arcad.employeebook.service.api.EmployeeServiceImpl;
import com.arcad.employeebook.service.impl.EmployeeService;
import com.arcad.employeebook.view.ViewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final ViewService viewService;
    private final EmployeeBookUtilite employeeBookUtilite;

    public EmployeeController(EmployeeServiceImpl employeeService, ViewService viewService, EmployeeBookUtilite employeeBookUtilite) {
        this.employeeService = employeeService;
        this.viewService = viewService;
        this.employeeBookUtilite = employeeBookUtilite;
    }

    @GetMapping(path = "/printAll")
//    @RequestParam("num1") String num1, @RequestParam("num2") String num2
    public String printAllEmployee() {
        List<Employee> result = employeeService.EmployeeAll();    //Список сотрудников
        return viewService.viewOutTable("Список всех сотрудников", result.toString());
    }

    @GetMapping(path = "/add")
//      Фамилия   Имя     Отчество    №Отдела Коэф.Оклада
//     {"Иванов", "Иван", "Иванович", "1", "1.2"},
//    firstName=FName&lastName=LName&midleName=MName&departmentID=4&scaleRatio=1.3
    public String addEmploeey(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("midleName") String midleName,
                              @RequestParam("departmentID") String departmentID,
                              @RequestParam("scaleRatio") String scaleRatio) {
        String result;
        List<String> argsString = new java.util.ArrayList<>(List.of(firstName, lastName, midleName));
        List<String> argsNumb = List.of(departmentID, scaleRatio);
        if (employeeBookUtilite.isReqParamString(argsString) &
                employeeBookUtilite.isReqParamNum(argsNumb)) {
            argsString.addAll(argsNumb);
            result = employeeService.addEmploeey(argsString);
        } else {
            result = "<tr><td> Введены не верные данные </td></tr>" +
                "<tr><td>" + argsString + "</td></tr>";
        }

        return viewService.viewOutTable("Добавление сотрудника", result);
    }
}
