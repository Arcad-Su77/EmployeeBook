package com.arcad.employeebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping
    public String index() {
        return "index";
    }
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    @GetMapping("/error")
    public String error(Model model) {
        model.addAttribute("message", "Hello, World!");
        return "error";
    }
}
