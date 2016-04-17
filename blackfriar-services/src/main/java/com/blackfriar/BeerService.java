package com.blackfriar;

import java.util.List;
import java.util.Optional;


/**
 * Created by paulwatson on 06/03/2016.
 */
public interface BeerService {

    List<Beer> getAllBeers();

    Optional<Beer> getById(Long id);
}
