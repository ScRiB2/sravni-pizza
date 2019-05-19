package ru.scrib.spring.dao;

import ru.scrib.spring.entity.User;

public interface UserDAO {

    User findByUserName(String userName);

    void save(User user);

}
