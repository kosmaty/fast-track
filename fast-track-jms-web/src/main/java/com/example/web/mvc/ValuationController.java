package com.example.web.mvc;

import javax.servlet.http.Part;
import javax.validation.Valid;
import javax.xml.transform.stream.StreamSource;

import com.example.core.FruitValuation;
import com.example.core.FruitValuations;
import com.example.web.Publisher;
import com.example.web.RequestData;
import com.example.web.SessionData;
import com.example.web.ValuationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
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

    @Autowired
    private Unmarshaller unmarshaller;

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

    @GetMapping("/secure/bulk")
    public String bulkForm() {
        return "valuations/bulk";
    }

    @PostMapping("/secure/bulk")
    public String sendFromFile(@RequestParam("file")Part file) throws IOException{
        try (InputStream stream = file.getInputStream()) {
            FruitValuations valuations = (FruitValuations) unmarshaller.unmarshal(new StreamSource(stream));
            for (FruitValuation valuation : valuations.getValuations()) {
                publisher.sendFruitValuationMessage(valuation);
            }
        }
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
