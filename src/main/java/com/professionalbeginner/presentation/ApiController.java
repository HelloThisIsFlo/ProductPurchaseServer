package com.professionalbeginner.presentation;

import com.professionalbeginner.domain.application.driving.RetrieveValidPurchasesUseCase;
import com.professionalbeginner.domain.application.driving.RetrieveValidPurchasesUseCaseFactory;
import com.professionalbeginner.domain.application.driving.UseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * Main controller for the API.
 */
@Controller
@RequestMapping("/")
public class ApiController {

    @Autowired
    RetrieveValidPurchasesUseCaseFactory useCaseFactory;

    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
        return "<h1>Hello</h1>";
    }


}
