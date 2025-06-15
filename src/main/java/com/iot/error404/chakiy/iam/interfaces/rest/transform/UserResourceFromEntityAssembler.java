package com.iot.error404.chakiy.iam.interfaces.rest.transform;

import com.iot.error404.chakiy.iam.domain.model.aggregates.User;
import com.iot.error404.chakiy.iam.domain.model.entities.Role;
import com.iot.error404.chakiy.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {

  public static UserResource toResourceFromEntity(User user) {
    var roles = user.getRoles().stream()
            .map(Role::getStringName)
            .toList();
    return new UserResource(user.getId(), user.getEmail(), roles);
  }
}
