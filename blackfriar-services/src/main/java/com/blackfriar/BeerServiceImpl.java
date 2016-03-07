package com.blackfriar;

import java.util.Arrays;
import java.util.List;

/**
 * Created by paulwatson on 06/03/2016.
 */

public class BeerServiceImpl implements BeerService {

    @Override
    public List<com.blackfriar.Beer> getAllBeers() {

        return Arrays.asList(new Beer());
    }
}
