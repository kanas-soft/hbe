package com.kanas.hbe.service;

import com.kanas.hbe.domain.entity.Role;
import com.kanas.hbe.domain.enumeration.UserRole;
import com.kanas.hbe.service.base.BaseService;

public interface RoleService extends BaseService<Role> {

    Role findByRole(UserRole role);
}
