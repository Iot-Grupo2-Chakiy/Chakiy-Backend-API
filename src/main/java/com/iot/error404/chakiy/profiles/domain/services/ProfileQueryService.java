package com.iot.error404.chakiy.profiles.domain.services;

import com.iot.error404.chakiy.profiles.domain.model.aggregates.Profile;
import com.iot.error404.chakiy.profiles.domain.model.queries.GetAllProfilesQuery;
import com.iot.error404.chakiy.profiles.domain.model.queries.GetProfileByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);

    List<Profile> handle(GetAllProfilesQuery query);
}
