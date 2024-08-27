package com.raimiyashiro.challenge_meli_country_spy.country.domain;

import com.raimiyashiro.challenge_meli_country_spy.country.infraestructure.dto.CountryDetailsDTO;

import java.math.BigDecimal;

public interface OriginCountryService {
    OriginCountry findByIp(String ip);

    OriginCountry updateCountryInfo(OriginCountry originCountry);

    BigDecimal getCurrencyRateInUSD(String country);

    CountryDetailsDTO getCountryDetails(String ipAddress);
}
