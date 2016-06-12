package com.blackfriar.domain;

import org.hibernate.annotations.CollectionId;

import javax.persistence.*;


@SequenceGenerator(name = "seq_beer",
        sequenceName = "seq_beer",
        allocationSize = 1)
@Entity
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_beer")
    private Long id;

    @Column
    private String name;

    @Column(precision = 4)
    private Long price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
