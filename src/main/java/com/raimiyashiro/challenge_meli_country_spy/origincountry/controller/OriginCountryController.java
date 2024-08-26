package com.raimiyashiro.challenge_meli_country_spy.origincountry.controller;

import com.raimiyashiro.challenge_meli_country_spy.origincountry.domain.OriginCountry;
import com.raimiyashiro.challenge_meli_country_spy.origincountry.exception.ApiError;
import com.raimiyashiro.challenge_meli_country_spy.origincountry.exception.CountryNotFoundException;
import com.raimiyashiro.challenge_meli_country_spy.origincountry.exception.CurrencyNotFoundException;
import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.OriginCountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping(path = "/api/v1/origin-country")
@RequiredArgsConstructor
public class OriginCountryController {

    private final OriginCountryService originCountryService;

    @GetMapping
    public OriginCountry findByIp(@RequestParam(value = "ipAddress") String ipAddress) {
        return originCountryService.findByIp(ipAddress);
    }

    // TODO: move this to a separated ExceptionHandler class
    @ExceptionHandler(CountryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleCountryNotFoundException(CountryNotFoundException e) {
        return new ResponseEntity<>(new ApiError(e.getMessage(), ZonedDateTime.now()), HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(CurrencyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleCurrencyNotFoundException(CurrencyNotFoundException e) {
        return new ResponseEntity<>(new ApiError(e.getMessage(), ZonedDateTime.now()), HttpStatusCode.valueOf(404));
    }
}
