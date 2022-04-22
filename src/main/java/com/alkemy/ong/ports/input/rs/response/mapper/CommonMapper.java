package com.alkemy.ong.ports.input.rs.response.mapper;

import org.mapstruct.Named;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

interface CommonMapper {

    @Named("localDateTimeToDate")
    default Date localDateTimeToOffsetDateTime(LocalDateTime date) {
        return Optional.ofNullable(date).map(Timestamp::valueOf).orElse(null);
    }
}
