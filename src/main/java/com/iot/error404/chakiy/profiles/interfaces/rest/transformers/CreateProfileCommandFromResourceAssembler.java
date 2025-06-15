package com.iot.error404.chakiy.profiles.interfaces.rest.transformers;


import com.iot.error404.chakiy.profiles.domain.model.commands.CreateProfileCommand;
import com.iot.error404.chakiy.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(resource.firstName(), resource.lastName(), resource.birthDate(), resource.userId());
    }
}
