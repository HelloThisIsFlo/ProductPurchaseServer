package com.professionalbeginner.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Main controller for the API.
 */
@Controller
@RequestMapping("/")
public class ApiController {


    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
        return "<h1>Hello</h1>";
    }


}
