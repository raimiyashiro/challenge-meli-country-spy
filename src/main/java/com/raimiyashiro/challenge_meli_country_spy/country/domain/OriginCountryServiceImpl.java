package com.raimiyashiro.challenge_meli_country_spy.country.domain;

import com.raimiyashiro.challenge_meli_country_spy.country.application.exception.CountryNotFoundException;
import com.raimiyashiro.challenge_meli_country_spy.country.application.exception.CurrencyNotFoundException;
import com.raimiyashiro.challenge_meli_country_spy.country.infraestructure.dto.CountryDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OriginCountryServiceImpl implements OriginCountryService {

    private static final Logger logger = LoggerFactory.getLogger(OriginCountryServiceImpl.class);
    private final OriginCountryRepository originCountryRepository;
    private final CountryDetailsService countryDetailsService;
    private final CountryCurrencyService countryCurrencyService;

    @Cacheable(value = "country", key = "#ipAddress")
    @Override
    public OriginCountry findByIp(String ipAddress) {
        logger.info("Searching for country info for the ipAddress: {}", ipAddress);
        Optional<OriginCountry> existingCountryInfo = originCountryRepository.findById(ipAddress);

        if (existingCountryInfo.isPresent()) {
            OriginCountry countryInfo = existingCountryInfo.get();
            return countryInfo.isUpdated() ? countryInfo : updateCountryInfo(countryInfo);
        }

        CountryDetailsDTO countryDetails = getCountryDetails(ipAddress);
        BigDecimal currencyRateInUSD = getCurrencyRateInUSD(countryDetails.getLocale().getCountry());

        OriginCountry originCountry = OriginCountry.builder()
                .ipAddress(ipAddress)
                .name(countryDetails.getCountryName())
                .currencyRateInUSD(currencyRateInUSD)
                .locale(countryDetails.getLocale())
                .population(countryDetails.getPopulation())
                .updatedAt(ZonedDateTime.now())
                .build();

        return originCountryRepository.save(originCountry);
    }


    @Override
    public OriginCountry updateCountryInfo(OriginCountry originCountry) {
        logger.info("Updating country info for the ipAddress: {}", originCountry.getIpAddress());
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
