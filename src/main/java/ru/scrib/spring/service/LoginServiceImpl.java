package ru.scrib.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scrib.spring.dao.UserDAO;
import ru.scrib.spring.entity.AppUser;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDAO userDAO;

    @Transactional
    public void saveUser(AppUser user) {
        userDAO.saveUser(user);
    }
}
