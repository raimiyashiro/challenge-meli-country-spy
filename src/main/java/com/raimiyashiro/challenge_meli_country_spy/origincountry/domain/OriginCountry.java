package com.raimiyashiro.challenge_meli_country_spy.origincountry.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Locale;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "origin_country")
public class OriginCountry implements Serializable {
    @Id
    private String ipAddress;
    private String name;
    private Locale locale;
    private BigDecimal currencyRateInUSD;
    private ZonedDateTime updatedAt;
    private int population;

    public boolean isUpdated() {
        return ZonedDateTime.now().toLocalDate()
                .equals(this.getUpdatedAt().toLocalDate());
    }
}
