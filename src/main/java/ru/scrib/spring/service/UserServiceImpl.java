package ru.scrib.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.scrib.spring.dao.RoleDAO;
import ru.scrib.spring.dao.UserDAO;
import ru.scrib.spring.entity.Role;
import ru.scrib.spring.entity.UserApp;
import ru.scrib.spring.user.CrmUser;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    // need to inject user dao
    @Autowired
    private UserDAO userDao;

    @Autowired
    private RoleDAO roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public UserApp findByUserName(String userName) {
        // check the database if the user already exists
        return userDao.findByUserName(userName);
    }

    @Transactional
    public void save(CrmUser crmUser) {
        UserApp userApp = new UserApp();
        // assign userApp details to the userApp object
        userApp.setUserName(crmUser.getUserName());
        userApp.setPassword(passwordEncoder.encode(crmUser.getPassword()));
        userApp.setFirstName(crmUser.getFirstName());
        userApp.setLastName(crmUser.getLastName());
        userApp.setEmail(crmUser.getEmail());

        // give userApp default role of "employee"
        userApp.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));

        // save userApp in the database
        userDao.save(userApp);
    }

    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserApp userApp = userDao.findByUserName(userName);
        if (userApp == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(userApp.getUserName(), userApp.getPassword(),
                mapRolesToAuthorities(userApp.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
