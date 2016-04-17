package com.blackfriar;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by paulwatson on 06/03/2016.
 */
@Service
public class BeerServiceImpl implements BeerService {

    private static List<Beer> beers = new ArrayList<Beer>();

    static {

        Beer anchorSteam = new Beer();
        anchorSteam.setId(1L);
        anchorSteam.setName("Anchor Steam");
        anchorSteam.setPrice(10L);

        beers.add(anchorSteam);
    }


    @Override
    public List<Beer> getAllBeers() {
        return beers;
    }

    @Override
    public Optional<Beer> getById(Long id) {
        return beers.stream()
                .filter(b -> Objects.equals(b.getId(), id))
                .findAny();

    }
}
