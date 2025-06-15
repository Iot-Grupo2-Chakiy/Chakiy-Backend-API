package com.iot.error404.chakiy.profiles.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.Date;

public record ProfileResource(
        Long id,
        String firstName,
        String lastName,
        LocalDate birthDate,
        Long userId,
        Date createdAt,
        Date updatedAt
) {
}
