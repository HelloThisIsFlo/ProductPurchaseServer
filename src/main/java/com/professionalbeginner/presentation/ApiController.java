package com.professionalbeginner.presentation;

import com.professionalbeginner.domain.application.DetailsDTO;
import com.professionalbeginner.domain.application.PurchaseDTO;
import com.professionalbeginner.domain.application.driving.RetrieveValidPurchasesUseCase;
import com.professionalbeginner.domain.application.driving.RetrieveValidPurchasesUseCaseFactory;
import com.professionalbeginner.domain.application.driving.SaveOrUpdateUseCase;
import com.professionalbeginner.domain.application.driving.SaveOrUpdateUseCaseFactory;
import com.professionalbeginner.domain.core.Details;
import com.professionalbeginner.presentation.model.Date;
import com.professionalbeginner.presentation.model.PurchaseFE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    private Date currentDate = new Date();

    @RequestMapping("/")
    public String hello(Model model) {

        model.addAttribute("date", currentDate);
        model.addAttribute("toadd", new PurchaseFE());



        addValidPurchases(model);


        System.out.println(currentDate);
        return "main";
    }

    private void addValidPurchases(Model model) {
        model.addAttribute("purchases", getValidPurchases());
    }

    private String getValidPurchases() {
        RetrieveValidPurchasesUseCase validPurchasesUseCase = retrieveValidPurchasesUseCaseFactory.make(currentDate.getCurrentTime().atTime(0,0));
        return validPurchasesUseCase.execute();
    }

    @RequestMapping("/test")
    public String test() {
        return "main";
    }

    @RequestMapping(value = "/newDate", method = RequestMethod.POST)
    public String updateDate(@ModelAttribute Date date, Model model) {

        currentDate = date;
        model.addAttribute("date", date);

        return "redirect:/";
    }

    @RequestMapping(value = "/newPurchase", method = RequestMethod.POST)
    public String saveNewPurchase(@ModelAttribute PurchaseFE toAdd, Model model) {
        PurchaseDTO purchase = getPurchaseDTO(toAdd);

        SaveOrUpdateUseCase useCase = saveOrUpdateUseCaseFactory.make(purchase);
        useCase.execute();


        return "redirect:/";
    }

    private PurchaseDTO getPurchaseDTO(@ModelAttribute PurchaseFE toAdd) {
        int quantity = 0;
        try {
            quantity = Integer.parseInt(toAdd.getQuantity());
        } catch (NumberFormatException e) {
            //do nothing
        }

        double value = 0;
        try {
            value = Double.parseDouble(toAdd.getValue());
        } catch (NumberFormatException e) {
            //do nothing
        }

        DetailsDTO details = new DetailsDTO(-1, toAdd.getDescription(), quantity, value);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");


        LocalDateTime dateTime = LocalDateTime.MAX;
        try {
            LocalDate date = LocalDate.parse(toAdd.getExpires(), formatter);
            dateTime = date.atTime(0,0);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new PurchaseDTO(-1, toAdd.getType(), dateTime, details);
    }


}
