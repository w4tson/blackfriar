package com.blackfriar.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by paulwatson on 07/03/2016.
 */
public class BeerResource extends ResourceSupport {

    @JsonProperty
    private Long urn;

    @JsonProperty
    private String name;

    @JsonProperty
    private Long price;

    public Long getUrn() {
        return urn;
    }

    public void setUrn(Long urn) {
        this.urn = urn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
