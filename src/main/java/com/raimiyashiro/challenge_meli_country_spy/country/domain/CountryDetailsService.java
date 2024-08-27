package com.raimiyashiro.challenge_meli_country_spy.country.domain;


import com.raimiyashiro.challenge_meli_country_spy.country.infraestructure.dto.CountryDetailsDTO;

import java.util.Optional;

public interface CountryDetailsService {

    Optional<CountryDetailsDTO> getCountryDetails(String ip);
}
