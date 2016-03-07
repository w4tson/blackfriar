package com.example;

import com.blackfriar.BeerService;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@Controller
public class BeerController {

    @Inject
    private BeerService beerService;

    @RequestMapping("/")
    @ResponseBody
    String home() {

        return "Hello World!!!!";
    }

 
}