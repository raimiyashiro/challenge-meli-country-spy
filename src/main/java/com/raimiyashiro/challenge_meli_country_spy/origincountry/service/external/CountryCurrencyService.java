package com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external;


import java.math.BigDecimal;
import java.util.Optional;

public interface CountryCurrencyService {
    Optional<BigDecimal> getCurrencyInformation(String country);

}
