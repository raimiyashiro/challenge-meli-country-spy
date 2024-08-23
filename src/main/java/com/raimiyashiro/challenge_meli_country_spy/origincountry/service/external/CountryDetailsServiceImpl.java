package com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external;

import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external.dto.CountryDetailsDTO;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
public class CountryDetailsServiceImpl implements CountryDetailsService {
    private static final Locale ARGENTINA = new Locale("es", "AR");
    private static final String RANDOM_IP_FROM_BUENOS_AIRES = "181.30.125.198";

    @Override
    public Optional<CountryDetailsDTO> getCountryDetails(String ip) {
        if (ip.equals(RANDOM_IP_FROM_BUENOS_AIRES)) {
            CountryDetailsDTO countryDetails = CountryDetailsDTO.builder()
                    .countryName("Argentina")
                    .locale(ARGENTINA)
                    .population(46230000)
                    .build();
            return Optional.of(countryDetails);
        }
        return Optional.empty();
    }
}
