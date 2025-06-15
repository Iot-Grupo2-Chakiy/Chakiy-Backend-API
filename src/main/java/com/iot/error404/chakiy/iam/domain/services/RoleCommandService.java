package com.iot.error404.chakiy.iam.domain.services;

import com.iot.error404.chakiy.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
  void handle(SeedRolesCommand command);
}
