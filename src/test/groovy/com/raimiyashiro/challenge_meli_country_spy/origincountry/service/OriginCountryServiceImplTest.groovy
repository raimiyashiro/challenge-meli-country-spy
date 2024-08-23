package com.raimiyashiro.challenge_meli_country_spy.origincountry.service

import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external.CountryCurrencyService
import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external.CountryDetailsService
import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external.dto.CountryDetailsDTO
import spock.lang.Specification
import spock.lang.Subject

class OriginCountryServiceImplTest extends Specification {
    def detailsService = Mock(CountryDetailsService)
    def currencyService = Mock(CountryCurrencyService)

    @Subject
    def subject = new OriginCountryServiceImpl(detailsService, currencyService)


    def "given a valid ip address, should return country info"() {
        given:
        def ipAddress = "181.56.0.1"
        def expectedDetails = CountryDetailsDTO.builder()
                .countryName("Argentina")
                .locale(new Locale("es", "AR"))
                .population(1000)
                .build()
        def expectedCurrency = new Random().nextDouble()

        and: "external services respond as expected"
        detailsService.getCountryDetails(ipAddress) >> Optional.of(expectedDetails)
        1 * currencyService.getCurrencyInformation(_) >> expectedCurrency

        when:
        def result = subject.findByIp(ipAddress)

        then:
        result.countryName == expectedDetails.countryName
        result.currencyRateInUSD == expectedCurrency
        result.locale == expectedDetails.locale
        result.population == expectedDetails.population
    }
}
