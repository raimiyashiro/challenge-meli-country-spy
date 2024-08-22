package com.raimiyashiro.challenge_meli_country_spy.origincountry.service;

import com.raimiyashiro.challenge_meli_country_spy.origincountry.domain.OriginCountry;

public interface OriginCountryService {
    OriginCountry findByIp(String ip);
}
