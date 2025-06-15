package com.iot.error404.chakiy.iam.interfaces.rest.transform;

import com.iot.error404.chakiy.iam.domain.model.entities.Role;
import com.iot.error404.chakiy.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {

  public static RoleResource toResourceFromEntity(Role role) {
    return new RoleResource(role.getId(), role.getStringName());
  }
}
