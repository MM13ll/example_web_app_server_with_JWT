package uz.course.hibernate.dao.impl;

import org.springframework.stereotype.Repository;
import uz.course.hibernate.base.DaoImpl;
import uz.course.hibernate.dao.UserSessionDao;
import uz.course.hibernate.domain._UserSession;

@Repository(value = "userSessionDao")
public class UserSessionDaoImpl extends DaoImpl<_UserSession> implements UserSessionDao {
    public UserSessionDaoImpl() {
        super(_UserSession.class);
    }
}
