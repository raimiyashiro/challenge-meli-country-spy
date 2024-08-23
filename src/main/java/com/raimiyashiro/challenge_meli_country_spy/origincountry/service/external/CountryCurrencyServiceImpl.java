package com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Optional;

@Service
public class CountryCurrencyServiceImpl implements CountryCurrencyService {
    private static final Locale ARGENTINA = new Locale("es", "AR");

    @Override
    public Optional<BigDecimal> getCurrencyInformation(String country) {
        if (country != null && country.equals(ARGENTINA.getCountry())){
            return Optional.of(BigDecimal.valueOf(0.0011));
        }
        return Optional.empty();
    }
}
