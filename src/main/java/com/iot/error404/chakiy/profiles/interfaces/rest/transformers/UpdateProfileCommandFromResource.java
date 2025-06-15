package com.iot.error404.chakiy.profiles.interfaces.rest.transformers;

import com.iot.error404.chakiy.profiles.domain.model.commands.UpdateProfileCommand;
import com.iot.error404.chakiy.profiles.interfaces.rest.resources.UpdateProfileResource;

public class UpdateProfileCommandFromResource {
    public static UpdateProfileCommand toCommandFromResource(Long userId, UpdateProfileResource resource) {
        return new UpdateProfileCommand(userId, resource.firstName(), resource.lastName(), resource.birthDate());
    }
}
