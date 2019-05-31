package ru.scrib.spring.dao;

import ru.scrib.spring.entity.user.Role;

public interface RoleDAO {

    Role findRoleByName(String theRoleName);
}
