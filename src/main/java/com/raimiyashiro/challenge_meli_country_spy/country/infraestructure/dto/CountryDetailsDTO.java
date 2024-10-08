package com.raimiyashiro.challenge_meli_country_spy.country.infraestructure.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Locale;

@Data
@Builder
public class CountryDetailsDTO {
    private String countryName;
    private Locale locale;
    private int population;
}
