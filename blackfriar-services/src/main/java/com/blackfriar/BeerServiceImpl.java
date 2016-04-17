package com.blackfriar;

import com.blackfriar.domain.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by paulwatson on 06/03/2016.
 */
@Service
public class BeerServiceImpl implements BeerService {

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
        return Optional.ofNullable(beerRepository.findOne(id));
    }
}
