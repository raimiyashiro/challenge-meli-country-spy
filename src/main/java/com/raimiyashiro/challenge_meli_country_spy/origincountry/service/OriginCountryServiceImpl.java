package com.raimiyashiro.challenge_meli_country_spy.origincountry.service;

import com.raimiyashiro.challenge_meli_country_spy.origincountry.domain.OriginCountry;
import com.raimiyashiro.challenge_meli_country_spy.origincountry.exception.CountryNotFoundException;
import com.raimiyashiro.challenge_meli_country_spy.origincountry.exception.CurrencyNotFoundException;
import com.raimiyashiro.challenge_meli_country_spy.origincountry.repository.OriginCountryRepository;
import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external.CountryCurrencyService;
import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external.CountryDetailsService;
import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external.dto.CountryDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OriginCountryServiceImpl implements OriginCountryService {

    private final OriginCountryRepository originCountryRepository;

    // Since these are just mocks (they are meant to be "external"), I'll keep it simple
    private final CountryDetailsService countryDetailsService;
    private final CountryCurrencyService countryCurrencyService;

    @Cacheable(value = "country", key = "#ipAddress")
    @Override
    public OriginCountry findByIp(String ipAddress) {
        Optional<OriginCountry> existingCountryInfo = originCountryRepository.findById(ipAddress);

        if (existingCountryInfo.isPresent()) {
            OriginCountry countryInfo = existingCountryInfo.get();
            return countryInfo.isUpdated() ? countryInfo : updateCountryInfo(countryInfo);
        }

        CountryDetailsDTO countryDetails = getCountryDetails(ipAddress);
        BigDecimal currencyRateInUSD = getCurrencyRateInUSD(countryDetails.getLocale().getCountry());

        OriginCountry originCountry = OriginCountry.builder()
                .ipAddress(ipAddress)
                .countryName(countryDetails.getCountryName())
                .currencyRateInUSD(currencyRateInUSD)
                .locale(countryDetails.getLocale())
                .population(countryDetails.getPopulation())
                .updatedAt(ZonedDateTime.now())
                .build();

        return originCountryRepository.save(originCountry);
    }


    @Override
    public OriginCountry updateCountryInfo(OriginCountry originCountry) {
        originCountry.setCurrencyRateInUSD(getCurrencyRateInUSD(originCountry.getLocale().getCountry()));
        originCountry.setPopulation(getCountryDetails(originCountry.getIpAddress()).getPopulation());
        originCountry.setUpdatedAt(ZonedDateTime.now());
        return originCountryRepository.save(originCountry);
    }

    @Override
    public BigDecimal getCurrencyRateInUSD(String country) {
        return countryCurrencyService.getCurrencyInformation(country).orElseThrow(
                () -> new CurrencyNotFoundException(String.format("Currency information unavailable for country: %s",
                        country)));
    }

    @Override
    public CountryDetailsDTO getCountryDetails(String ipAddress) {
        return countryDetailsService.getCountryDetails(ipAddress).orElseThrow(
                () -> new CountryNotFoundException(String.format("There are no details available for IP Address: %s",
                        ipAddress)));
    }
}
