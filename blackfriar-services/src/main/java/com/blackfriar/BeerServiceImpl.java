package com.blackfriar;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by paulwatson on 06/03/2016.
 */
@Service
public class BeerServiceImpl implements BeerService {

    @Override
    public List<Beer> getAllBeers() {

        return Arrays.asList(new Beer());
    }
}
