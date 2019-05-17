package ru.scrib.spring.dao;

import ru.scrib.spring.entity.UserApp;

public interface UserDAO {

    UserApp findByUserName(String userName);

    void save(UserApp userApp);

}
