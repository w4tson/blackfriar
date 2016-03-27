package com.blackfriar;

import com.blackfriar.assemblers.BeerResourceAssembler;
import com.blackfriar.resources.BeerResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/", produces = MediaTypes.HAL_JSON_VALUE)
public class BeerController {

    @Autowired
    private BeerService beerService;

    @Autowired
    private BeerResourceAssembler beerResourceAssembler;


    @RequestMapping("beer")
    @ResponseBody
    BeerResource home() {
        BeerResource beerResource = new BeerResource();
        beerResource.setName("Anchor Steam");
        beerResource.setPrice(123l);
        beerResource.setUrn(1l);
        return beerResource;
    }


}