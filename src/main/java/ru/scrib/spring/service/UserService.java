package ru.scrib.spring.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.scrib.spring.entity.UserApp;
import ru.scrib.spring.user.CrmUser;

public interface UserService extends UserDetailsService {
    UserApp findByUserName(String userName);

    void save(CrmUser crmUser);

}
