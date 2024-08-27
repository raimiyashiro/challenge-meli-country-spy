package com.raimiyashiro.challenge_meli_country_spy.country.domain;


import java.math.BigDecimal;
import java.util.Optional;

public interface CountryCurrencyService {
    Optional<BigDecimal> getCurrencyInformation(String country);

}
