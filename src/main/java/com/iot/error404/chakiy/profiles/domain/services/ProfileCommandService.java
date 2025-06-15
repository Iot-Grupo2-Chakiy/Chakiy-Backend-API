package com.iot.error404.chakiy.profiles.domain.services;

import com.iot.error404.chakiy.profiles.domain.model.aggregates.Profile;
import com.iot.error404.chakiy.profiles.domain.model.commands.CreateProfileCommand;
import com.iot.error404.chakiy.profiles.domain.model.commands.DeleteProfileCommand;
import com.iot.error404.chakiy.profiles.domain.model.commands.UpdateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
    Optional<Profile> handle(UpdateProfileCommand command);
    void handle(DeleteProfileCommand command);
}