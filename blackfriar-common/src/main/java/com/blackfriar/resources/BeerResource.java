package com.blackfriar.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

/**
 * Created by paulwatson on 07/03/2016.
 */
@Relation(collectionRelation = "beers")
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
