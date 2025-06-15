package com.iot.error404.chakiy.profiles.interfaces.rest.transformers;

import com.iot.error404.chakiy.profiles.domain.model.aggregates.Profile;
import com.iot.error404.chakiy.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBirthDate(),
                entity.getUserId().userIdAsPrimitive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
