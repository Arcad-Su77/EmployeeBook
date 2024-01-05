package com.arcad.employeebook.view;

import org.springframework.stereotype.Service;

@Service
public class ViewService {

    public String viewOut(String title, String result) {
        String header = "<h1> Emploee Book </h1><h2>" + title +
                "</h2><br>==================================";
        String footer = "<br>==================================<br><a href=\"/\">HOME</a>";
        return header + result + footer;
    }
    public String viewOutTable(String title, String result) {
        String header = "<h1> Emploee Boor </h1><h2>" + title +
                "</h2><br>==================================";
        String tableStart = "<table><tbody>";
        String tableEnd = "</tbody></table>";
        String footer = "<br>==================================<br><a href=\"/\">HOME</a>";
        return header + tableStart + result + tableEnd + footer;
    }
}
