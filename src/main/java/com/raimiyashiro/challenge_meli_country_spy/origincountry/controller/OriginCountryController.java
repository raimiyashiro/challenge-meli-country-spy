package com.raimiyashiro.challenge_meli_country_spy.origincountry.controller;

import com.raimiyashiro.challenge_meli_country_spy.origincountry.domain.OriginCountry;
import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.OriginCountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/origin-country")
@RequiredArgsConstructor
public class OriginCountryController {

    private final OriginCountryService originCountryService;

    @GetMapping
    public OriginCountry findByIp(String ip) {
        return originCountryService.findByIp(ip);
    }
}
