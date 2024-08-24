package com.raimiyashiro.challenge_meli_country_spy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ChallengeMeliCountrySpyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeMeliCountrySpyApplication.class, args);
	}

}
