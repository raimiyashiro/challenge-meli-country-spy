package com.raimiyashiro.challenge_meli_country_spy.country.application.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(String message) {
        super(message);
    }
}
