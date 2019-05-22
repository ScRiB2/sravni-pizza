package ru.scrib.spring.dao;

import ru.scrib.spring.entity.user.User;

public interface UserDAO {

    User findByUserName(String userName);

    void save(User user);

}
