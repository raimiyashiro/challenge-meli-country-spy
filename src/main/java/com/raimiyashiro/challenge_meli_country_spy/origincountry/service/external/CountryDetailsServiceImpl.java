package com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external;

import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external.dto.CountryDetailsDTO;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
public class CountryDetailsServiceImpl implements CountryDetailsService {
    @Override
    public Optional<CountryDetailsDTO> getCountryDetails(String ip) {
        if (ip != null && ip.startsWith("181")) {
            CountryDetailsDTO countryDetails = CountryDetailsDTO.builder()
                    .countryName("Argentina")
                    .locale(new Locale("es", "AR"))
                    .population(46230000)
                    .build();
            return Optional.of(countryDetails);
        }
        return Optional.empty();
    }
}
