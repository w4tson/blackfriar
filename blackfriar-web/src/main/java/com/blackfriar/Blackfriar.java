package com.blackfriar;

import com.blackfriar.domain.Beer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = { "com.blackfriar" })
public class Blackfriar {



	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Blackfriar.class, args);
        //BeerRepository beerRepo = context.getBean(BeerRepository.class);
//
//        Beer beer = new Beer();
//        beer.setId(1L);
//        beer.setName("Beavertoon");
//        beer.setPrice(30L);
//        beerRepo.save(beer);

    }
}
