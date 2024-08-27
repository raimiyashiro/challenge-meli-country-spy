package com.raimiyashiro.challenge_meli_country_spy.country.infraestructure;

import com.raimiyashiro.challenge_meli_country_spy.country.domain.CountryDetailsService;
import com.raimiyashiro.challenge_meli_country_spy.country.infraestructure.dto.CountryDetailsDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Service
public class CountryDetailsServiceImpl implements CountryDetailsService {

    private static final Locale ARGENTINA = new Locale("es", "AR");
    private static final Locale BRAZIL = new Locale("pt", "BR");
    private static final Locale USA = new Locale("en", "US");
    private static final Locale GERMANY = new Locale("de", "DE");
    private static final Locale JAPAN = new Locale("ja", "JP");

    private final Map<String, CountryDetailsDTO> countryDetailsMap;

    public CountryDetailsServiceImpl() {
        this.countryDetailsMap = new HashMap<>();

        countryDetailsMap.put("181.30.125.198", CountryDetailsDTO.builder()
                .countryName("Argentina")
                .locale(ARGENTINA)
                .population(46230000)
                .build());

        countryDetailsMap.put("200.20.120.10", CountryDetailsDTO.builder()
                .countryName("Brazil")
                .locale(BRAZIL)
                .population(213000000)
                .build());

        countryDetailsMap.put("8.8.8.8", CountryDetailsDTO.builder()
                .countryName("United States")
                .locale(USA)
                .population(331000000)
                .build());

        countryDetailsMap.put("81.169.145.72", CountryDetailsDTO.builder()
                .countryName("Germany")
                .locale(GERMANY)
                .population(83200000)
                .build());

        countryDetailsMap.put("153.126.207.255", CountryDetailsDTO.builder()
                .countryName("Japan")
                .locale(JAPAN)
                .population(126000000)
                .build());

        countryDetailsMap.put("121.78.88.99", CountryDetailsDTO.builder()
                .countryName("South Korea")
                .locale(new Locale("ko", "KR"))
                .population(51700000)
                .build());

        countryDetailsMap.put("5.189.191.62", CountryDetailsDTO.builder()
                .countryName("Netherlands")
                .locale(new Locale("nl", "NL"))
                .population(17400000)
                .build());

        countryDetailsMap.put("14.192.200.200", CountryDetailsDTO.builder()
                .countryName("India")
                .locale(new Locale("hi", "IN"))
                .population(1380000000)
                .build());

        countryDetailsMap.put("78.41.204.230", CountryDetailsDTO.builder()
                .countryName("Ukraine")
                .locale(new Locale("uk", "UA"))
                .population(44300000)
                .build());

        countryDetailsMap.put("43.224.110.133", CountryDetailsDTO.builder()
                .countryName("Australia")
                .locale(new Locale("en", "AU"))
                .population(25600000)
                .build());
    }

    @Override
    public Optional<CountryDetailsDTO> getCountryDetails(String ip) {
        return Optional.ofNullable(countryDetailsMap.get(ip));
    }
}