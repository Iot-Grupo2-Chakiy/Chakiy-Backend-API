package com.iot.error404.chakiy.profiles.application.internal.queryservices;

import com.iot.error404.chakiy.profiles.domain.model.aggregates.Profile;
import com.iot.error404.chakiy.profiles.domain.model.queries.GetAllProfilesQuery;
import com.iot.error404.chakiy.profiles.domain.model.queries.GetProfileByIdQuery;
import com.iot.error404.chakiy.profiles.domain.services.ProfileQueryService;
import com.iot.error404.chakiy.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {
    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query)
    {
        return profileRepository.findById(query.userId());
    }

    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {
        return profileRepository.findAll();
    }


}
