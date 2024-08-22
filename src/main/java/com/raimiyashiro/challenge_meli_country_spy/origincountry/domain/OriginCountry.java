package com.raimiyashiro.challenge_meli_country_spy.origincountry.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Locale;

@Data
@Builder
public class OriginCountry {
    private String countryName;
    private Locale locale;
    private double currencyRateInUSD;
    private int population;
}
