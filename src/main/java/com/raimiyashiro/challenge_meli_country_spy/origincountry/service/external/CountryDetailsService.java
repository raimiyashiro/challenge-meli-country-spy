package com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external;


import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external.dto.CountryDetailsDTO;

import java.util.Optional;

public interface CountryDetailsService {

    Optional<CountryDetailsDTO> getCountryDetails(String ip);
}
