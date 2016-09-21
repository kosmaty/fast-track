package com.example.web.mvc;

import javax.validation.Valid;

import com.example.core.FruitValuation;
import com.example.web.Publisher;
import com.example.web.RequestData;
import com.example.web.SessionData;
import com.example.web.ValuationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;


@Controller
@RequestMapping("/")
public class ValuationController {

    private Publisher publisher;
    private ValuationRepository valuationRepository;

    @Autowired
    private SessionData sessionData;

    @Autowired
    private RequestData requestData;

    public ValuationController(Publisher publisher, ValuationRepository valuationRepository) {
        this.publisher = publisher;
        this.valuationRepository = valuationRepository;
    }

    @GetMapping
    public ModelAndView list() {
        Iterable<FruitValuation> valuations = valuationRepository.allValuations();
        return new ModelAndView("valuations/list", "valuations", valuations);
    }

    @GetMapping("/secure/form")
    public String createForm(@ModelAttribute FruitValuation valuation) {
        return "valuations/form";
    }

    @PostMapping("/secure/form")
    public String create(@Valid FruitValuation fruitValuation, BindingResult result,
                         RedirectAttributes redirect) {
        fruitValuation.setTime(new Date());
        publisher.sendFruitValuationMessage(fruitValuation);
        return "redirect:/";
    }

    @ModelAttribute("sessionStartTime")
    public Date sessionStartTime() {
        return sessionData.getSessionStartDate();
    }

    @ModelAttribute("requestTime")
    public Date requestTime() {
        return requestData.getRequestTime();
    }
}
