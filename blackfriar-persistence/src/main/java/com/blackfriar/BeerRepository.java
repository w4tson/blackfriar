package com.blackfriar;

import com.blackfriar.domain.Beer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by paulwatson on 17/04/2016.
 */
public interface BeerRepository extends CrudRepository<Beer, Long> {
}
