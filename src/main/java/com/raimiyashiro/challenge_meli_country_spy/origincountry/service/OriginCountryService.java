package com.raimiyashiro.challenge_meli_country_spy.origincountry.service;

import com.raimiyashiro.challenge_meli_country_spy.origincountry.domain.OriginCountry;
import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external.dto.CountryDetailsDTO;

import java.math.BigDecimal;

public interface OriginCountryService {
    OriginCountry findByIp(String ip);

    OriginCountry updateCountryInfo(OriginCountry originCountry);

    BigDecimal getCurrencyRateInUSD(String country);

    CountryDetailsDTO getCountryDetails(String ipAddress);
}
