package com.iot.error404.chakiy.profiles.infrastructure.persistence.jpa.repositories;


import com.iot.error404.chakiy.profiles.domain.model.aggregates.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
