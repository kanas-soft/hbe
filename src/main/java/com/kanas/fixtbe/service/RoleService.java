package com.kanas.fixtbe.service;

import com.kanas.fixtbe.domain.entity.Role;
import com.kanas.fixtbe.domain.enumeration.UserRole;
import com.kanas.fixtbe.service.base.BaseService;

public interface RoleService extends BaseService<Role> {

    Role findByUserRole(UserRole role);
}
