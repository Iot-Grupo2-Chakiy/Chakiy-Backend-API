package com.iot.error404.chakiy.iam.interfaces.rest.transform;

import com.iot.error404.chakiy.iam.domain.model.aggregates.User;
import com.iot.error404.chakiy.iam.domain.model.entities.Role;
import com.iot.error404.chakiy.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {

  public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
    var roles = user.getRoles().stream()
            .map(Role::getStringName)
            .toList();
    return new AuthenticatedUserResource(user.getId(), user.getEmail(), token, roles);
  }
}