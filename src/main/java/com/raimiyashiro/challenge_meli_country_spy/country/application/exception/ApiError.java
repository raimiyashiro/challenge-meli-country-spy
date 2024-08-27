package com.raimiyashiro.challenge_meli_country_spy.country.application.exception;

import java.time.ZonedDateTime;

public record ApiError(String message, ZonedDateTime zonedDateTime) {
}
