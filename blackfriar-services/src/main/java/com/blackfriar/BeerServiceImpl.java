package com.blackfriar;

import com.blackfriar.domain.Beer;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    BeerRepository beerRepository;


    @Override
    public List<Beer> getAllBeers() {
        List<Beer> beers = new ArrayList<>();
        beerRepository.findAll().forEach(beers::add);
        return beers;
    }

    @Override
    public Optional<Beer> getById(Long id) {
        List<Beer> beers = new ArrayList<>();
        beerRepository.findAll().forEach(beers::add);
        return beers.stream()
                .filter(b -> Objects.equals(b.getId(), id))
                .findAny();

    }
}
