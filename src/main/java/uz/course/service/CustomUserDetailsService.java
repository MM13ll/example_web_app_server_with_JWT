package uz.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uz.course.hibernate.dao.UserDao;
import uz.course.hibernate.domain._User;
import uz.course.jwt.UserPrincipal;
import uz.course.to.RpcException;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        _User user = userDao.findByPhone(userName);
        if (user == null) throw new RpcException("User not found");

        return UserPrincipal.create(user);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public UserDetails loadUserById(Long id) {
        _User user = userDao.get(id);
        if (user == null) throw new RpcException("User not found");

        return UserPrincipal.create(user);
    }
}