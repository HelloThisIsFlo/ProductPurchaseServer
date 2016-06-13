package com.professionalbeginner.presentation;

import com.professionalbeginner.domain.application.driving.RetrieveValidPurchasesUseCaseFactory;
import com.professionalbeginner.domain.application.driving.SaveOrUpdateUseCaseFactory;
import com.professionalbeginner.domain.core.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Main controller for the API.
 */
@Controller
@RequestMapping("/")
public class ApiController {

    @Autowired
    SaveOrUpdateUseCaseFactory saveOrUpdateUseCaseFactory;
    @Autowired
    RetrieveValidPurchasesUseCaseFactory retrieveValidPurchasesUseCaseFactory;

    @RequestMapping("dev")
    @ResponseBody
    public String hello() {
        return "<h1>Hello</h1>";
    }


}
