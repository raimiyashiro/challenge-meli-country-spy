package com.raimiyashiro.challenge_meli_country_spy.origincountry.service

import com.raimiyashiro.challenge_meli_country_spy.origincountry.domain.OriginCountry
import com.raimiyashiro.challenge_meli_country_spy.origincountry.repository.OriginCountryRepository
import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external.CountryCurrencyService
import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external.CountryDetailsService
import com.raimiyashiro.challenge_meli_country_spy.origincountry.service.external.dto.CountryDetailsDTO
import spock.lang.Specification
import spock.lang.Subject

import java.time.ZonedDateTime

class OriginCountryServiceImplTest extends Specification {
    def repository = Mock(OriginCountryRepository)
    def detailsService = Mock(CountryDetailsService)
    def currencyService = Mock(CountryCurrencyService)

    @Subject
    def subject = new OriginCountryServiceImpl(repository, detailsService, currencyService)


    def "given a valid ip address, should return country info"() {
        given:
        def ipAddress = "181.56.0.1"
        def expectedDetails = CountryDetailsDTO.builder()
                .countryName("Argentina")
                .locale(new Locale("es", "AR"))
                .population(1000)
                .build()
        def expectedCurrency = 0.001

        and: "the ip has not yet been consulted"
        repository.findById(ipAddress) >> Optional.empty()

        and: "external services respond as expected"
        detailsService.getCountryDetails(ipAddress) >> Optional.of(expectedDetails)
        1 * currencyService.getCurrencyInformation(_) >> Optional.of(expectedCurrency)

        and: "the new ip is persisted"
        1 * repository.save(_) >> { OriginCountry country -> return country }

        when:
        def result = subject.findByIp(ipAddress)

        then:
        result.name == expectedDetails.countryName
        result.currencyRateInUSD == expectedCurrency
        result.locale == expectedDetails.locale
        result.population == expectedDetails.population
    }

    def "given an existing ip address, should return updated country info"() {
        given:
        def ipAddress = "181.56.0.1"
        def existingCountryInfo = OriginCountry.builder()
                .ipAddress(ipAddress)
                .name("Argentina")
                .locale(new Locale("es", "AR"))
                .population(1000)
                .currencyRateInUSD(0.001)
                .updatedAt(ZonedDateTime.now().minusDays(1))
                .build()

        repository.findById(ipAddress) >> Optional.of(existingCountryInfo)

        and: "external services respond as expected"
        def (newPopulation, newCurrencyRate) = [2000, 0.002]
        detailsService.getCountryDetails(ipAddress) >> Optional.of(CountryDetailsDTO.builder().population(newPopulation)
                .build())
        1 * currencyService.getCurrencyInformation(existingCountryInfo.locale.country) >> Optional.of(newCurrencyRate)

        and: "updated info is persisted"
        1 * repository.save(_) >> { OriginCountry country -> return country }

        when:
        def result = subject.findByIp(ipAddress)

        then:
        result.isUpdated()
        result.population == newPopulation
        result.currencyRateInUSD == newCurrencyRate
    }
}
