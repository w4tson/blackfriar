package com.blackfriar.assemblers;

import com.blackfriar.domain.Beer;
import com.blackfriar.controllers.BeerController;
import com.blackfriar.resources.BeerResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by paulwatson on 07/03/2016.
 */
@Component
public class BeerResourceAssembler extends ResourceAssemblerSupport<Beer, BeerResource> {



    public BeerResourceAssembler() {
        super(BeerController.class, BeerResource.class);
    }

    /**
     * Creates a new {@link ResourceAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public BeerResourceAssembler(Class<?> controllerClass, Class<BeerResource> resourceType) {
        super(controllerClass, resourceType);
    }


    @Override
    public BeerResource toResource(Beer entity) {
        BeerResource beerResource = new BeerResource();
        beerResource.setName(entity.getName());
        beerResource.setPrice(entity.getPrice());
        beerResource.setUrn(entity.getId());


        beerResource.add(linkTo(methodOn(BeerController.class).findById(entity.getId())).withSelfRel());


        return beerResource;
    }
}
