package com.blackfriar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by paulwatson on 16/04/2016.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Beer")  // 404
public class BeerNotFoundException extends RuntimeException {

    public BeerNotFoundException() {
        super();
    }

    public BeerNotFoundException(String message) {
        super(message);
    }

}
