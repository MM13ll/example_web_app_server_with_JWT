package uz.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import uz.course.config.ApplicationContextProvider;
import uz.course.hibernate.dao.RoleDao;
import uz.course.hibernate.dao.UserDao;
import uz.course.hibernate.dao.UserSessionDao;
import uz.course.hibernate.domain._User;
import uz.course.hibernate.domain._UserSession;
import uz.course.to.ChangePassword;
import uz.course.to.RpcException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserSessionDao userSessionDao;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public _User findByUserName(String userName) {
        return userDao.findByPhone(userName);
    }

    @Transactional
    public _UserSession createSession(Authentication authentication, HttpServletRequest httpServletRequest, Cookie cookie) {
        UserService userService = ApplicationContextProvider.applicationContext.getBean(UserService.class);

        SessionUtils.getInstance().setSessionId(cookie.getValue());
        _User user = userService.findByUserName(authentication.getName());
        _UserSession userSession = new _UserSession();
        userSession.setUser(user);
        userSession.setToken(cookie.getValue());
        userSession.setIPAddress(getClientIpAddr(httpServletRequest));
        userSession.setSigniinDate(Instant.now());
        userSession.setUuid(UUID.randomUUID().toString());
        userSessionDao.save(userSession);

        return userSession;
    }

    public String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_X_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_VIA");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getRemoteAddr();
        }
        System.out.println(ip);
        return ip;
    }


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<String> getCurrentUserRoles() {
        if (SessionUtils.getInstance().getUser() == null) return Collections.emptyList();
        return SessionUtils.getInstance().getRoles();
    }

    @Transactional
    public void changePassword(ChangePassword changePassword) {
        if (changePassword == null || StringUtils.isEmpty(changePassword.getPassword()))
            throw new RpcException("Паролни киритинш!");
        if (StringUtils.isEmpty(changePassword.getNewPassword()))
            throw new RpcException("Янги паролни киритинш!");
        if (!changePassword.getNewPassword().equals(changePassword.getConfirmPassword()))
            throw new RpcException("Янги паролни тасдиқланг");
        _User user = userDao.getUser();
        if (!passwordEncoder.matches(changePassword.getPassword(), user.getPassword()))
            throw new RpcException("Паролни тўғри киритинг!");
        user.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
        userDao.save(user);
        userDao.getSession().flush();
        userDao.getSession().clear();
    }
}