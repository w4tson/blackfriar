package com.blackfriar;

import com.blackfriar.domain.Beer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * Created by paulwatson on 06/03/2016.
 */
@Transactional(rollbackFor = Exception.class) //By default spring only rollsback on runtime exceptions
public interface BeerService {

    List<Beer> getAllBeers();

    Optional<Beer> getById(Long id);
}
