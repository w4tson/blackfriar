package com.blackfriar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BeerController {

    @Autowired
    private BeerService beerService;

    @RequestMapping("/")
    @ResponseBody
    String home() {

        return "Hello World!!!!";
    }


}