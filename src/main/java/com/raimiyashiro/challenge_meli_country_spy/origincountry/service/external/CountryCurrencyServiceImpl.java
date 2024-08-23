package com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external;

import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CountryCurrencyServiceImpl implements CountryCurrencyService {
    private static final Locale ARGENTINA = new Locale("es", "AR");

    @Override
    public double getCurrencyInformation(String country) {
        if (country != null && country.equals(ARGENTINA.getCountry())){
            return 0.0011;
        }
        return 0;
    }
}
