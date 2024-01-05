package com.arcad.employeebook.service.api;

import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeBookUtilite {
    String regEN = "abcdefghijklmnopqrstuwxyz-ABCDEFGHIJKLMNOPQRSTUWXYZ";
    String regRU = "абвгдеёжзийклмнопрстуфхцчшщьыъэюя-АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩБЫЪЭЮЯ";

    /**
     * @param argsString Список параметров для проверки
     * @return Возвращает истина если список корректно введен
     */
    public boolean isReqParamString(List<String> argsString) {
        boolean isReturn = false;
        for (String arg : argsString) {
            if (!arg.isEmpty() & (containsOnly(arg, regEN) | containsOnly(arg, regRU))) isReturn = true;
            else {
                isReturn = false;
                break;
            }
        }
        return isReturn;
    }
    public boolean isReqParamNum(List<String> argsNumb) {
        return true;
    }
}
