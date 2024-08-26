package com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CountryCurrencyServiceImpl implements CountryCurrencyService {

    private final Map<String, BigDecimal> currencyMap;

    public CountryCurrencyServiceImpl() {
        this.currencyMap = new HashMap<>();

        // Populate the map with 10 countries and their corresponding currency rates
        currencyMap.put("AR", BigDecimal.valueOf(0.0011)); // Argentina
        currencyMap.put("BR", BigDecimal.valueOf(0.20));   // Brazil
        currencyMap.put("US", BigDecimal.valueOf(1.00));   // United States
        currencyMap.put("DE", BigDecimal.valueOf(1.10));   // Germany
        currencyMap.put("JP", BigDecimal.valueOf(0.0075)); // Japan

        currencyMap.put("KR", BigDecimal.valueOf(0.00085)); // South Korea
        currencyMap.put("NL", BigDecimal.valueOf(1.05));    // Netherlands
        currencyMap.put("IN", BigDecimal.valueOf(0.013));   // India
        currencyMap.put("UA", BigDecimal.valueOf(0.027));   // Ukraine
        currencyMap.put("AU", BigDecimal.valueOf(0.64));    // Australia
    }

    @Override
    public Optional<BigDecimal> getCurrencyInformation(String country) {
        return Optional.ofNullable(currencyMap.get(country));
    }
}

