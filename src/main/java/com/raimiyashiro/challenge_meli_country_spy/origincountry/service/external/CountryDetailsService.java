package com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external;


import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external.dto.CountryDetailsDTO;

public interface CountryDetailsService {

    CountryDetailsDTO getCountryDetails(String ip);
}
