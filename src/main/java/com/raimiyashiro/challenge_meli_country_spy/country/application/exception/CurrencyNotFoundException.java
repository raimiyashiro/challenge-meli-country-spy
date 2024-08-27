package com.raimiyashiro.challenge_meli_country_spy.country.application.exception;

public class CurrencyNotFoundException extends RuntimeException {
    public CurrencyNotFoundException(String message) {
        super(message);
    }
}
