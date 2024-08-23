package com.raimiyashiro.challenge_meli_country_spy.origincountry.service;

import com.raimiyashiro.challenge_meli_country_spy.origincountry.domain.OriginCountry;
import com.raimiyashiro.challenge_meli_country_spy.origincountry.exception.CountryNotFoundException;
import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external.CountryCurrencyService;
import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external.CountryDetailsService;
import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external.dto.CountryDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OriginCountryServiceImpl implements OriginCountryService {

    // Since these are just mocks (they are meant to be "external"), I'll keep it simple
    private final CountryDetailsService countryDetailsService;
    private final CountryCurrencyService countryCurrencyService;

    @Override
    public OriginCountry findByIp(String ipAddress) {
        // TODO: The next step would be adding a Cache Solution to avoid unnecessary calls to external APIs
        Optional<CountryDetailsDTO> countryDetails = countryDetailsService.getCountryDetails(ipAddress);
        if (countryDetails.isEmpty())
            throw new CountryNotFoundException(String.format("There are no details available for the IP Address: %s",
                    ipAddress));

        double countryCurrency = countryCurrencyService.getCurrencyInformation(countryDetails.get().getLocale().getCountry());

        return OriginCountry.builder()
                .countryName(countryDetails.get().getCountryName())
                .currencyRateInUSD(countryCurrency)
                .locale(countryDetails.get().getLocale())
                .population(countryDetails.get().getPopulation())
                .build();
    }
}
