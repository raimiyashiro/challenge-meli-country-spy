package com.raimiyashiro.challenge_meli_country_spy.origincountry.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Locale;

@Data
@Builder
@RedisHash("country")
public class OriginCountry {
    @Id
    private String ipAddress;
    private String countryName;
    private Locale locale;
    private BigDecimal currencyRateInUSD;
    private ZonedDateTime updatedAt;
    private int population;

    public boolean isUpdated() {
        return ZonedDateTime.now().toLocalDate()
                .equals(this.getUpdatedAt().toLocalDate());
    }
}
