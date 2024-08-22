package com.raimiyashiro.challenge_meli_country_spy.origincountry.service;

import com.raimiyashiro.challenge_meli_country_spy.origincountry.domain.OriginCountry;
import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external.CountryCurrencyService;
import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external.CountryDetailsService;
import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external.dto.CountryDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OriginCountryServiceImpl implements OriginCountryService {

    // Since these are just mocks (they are meant to be "external"), I'll keep it simple
    private final CountryDetailsService countryDetailsService;
    private final CountryCurrencyService countryCurrencyService;

    @Override
    public OriginCountry findByIp(String ip) {
        CountryDetailsDTO countryDetails = countryDetailsService.getCountryDetails(ip);
        double countryCurrency = countryCurrencyService.getCurrencyInformation(countryDetails.getLocale().getCountry());

        return OriginCountry.builder()
                .countryName(countryDetails.getCountryName())
                .currencyRateInUSD(countryCurrency)
                .locale(countryDetails.getLocale())
                .population(countryDetails.getPopulation())
                .build();
    }
}
