package ru.scrib.spring.dao;

import ru.scrib.spring.entity.Role;

public interface RoleDAO {

    Role findRoleByName(String theRoleName);
}
