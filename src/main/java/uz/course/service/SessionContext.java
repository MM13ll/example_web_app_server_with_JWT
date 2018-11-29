package uz.course.service;

import org.hibernate.query.Query;
import uz.course.hibernate.domain._User;
import uz.course.hibernate.domain._UserSession;
import uz.course.to.Constants;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by Virus on 19-Sep-16.
 */
public class SessionContext extends SessionUtils {

    public static String USER_QUERY = "select us.user from _UserSession us where us.token=:sessionId and expired = false";
    public static String USER_SESSION_QUERY = "select us from _UserSession us where us.token=:sessionId and expired = false";
    public static String ROLE_QUERY = "select concat('ROLE_',r.code) as code from _User t join t.roles r where t.id = :user and t.state <> 2";

    private long startDate;
    private String sessionId;
    private String language = "en";
    private _User user;
    private List<String> roles;

    @Override
    public long getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
        this.user = null;
        this.roles = null;
    }

    @Override
    public boolean isLoggedIn() {
        return sessionId != null && sessionId.length() > 0;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public _User getUser() {
        if (user != null) {
            return user;
        }
        try {
            user = getUserBySession();

            if (user == null && sessionId != null && !sessionId.contains("(EXPIRED)")) {//Case when session is expired
                sessionId = sessionId + "(EXPIRED)";
            }
            return user;
        } catch (EntityNotFoundException exception) {
            return null;
        }
    }

    @Override
    public List<String> getRoles() {
        if (roles != null)
            return roles;

        return getRoleByUser();
    }


    public _User getUserBySession() {
        if (!isLoggedIn()) {
            return null;
        }

        org.hibernate.Session session;
        if (getSessionFactory().isClosed()) {
            session = getSessionFactory().openSession();
        } else {
            session = getSessionFactory().getCurrentSession();
        }
        if (session.isOpen()) {
            Query<_User> queryObject = session.createQuery(USER_QUERY, _User.class);
            queryObject.setParameter("sessionId", sessionId);
            queryObject.setMaxResults(1);
            queryObject.setCacheable(true);
            queryObject.setCacheRegion(Constants.Cache.QUERY_USER);
            _User object = (_User) queryObject.uniqueResult();
            return object;
        }
        return null;
    }

    private List<String> getRoleByUser() {
        if (getUser() == null)
            return null;

        org.hibernate.Session session;
        if (getSessionFactory().isClosed()) {
            session = getSessionFactory().openSession();
        } else {
            session = getSessionFactory().getCurrentSession();
        }
        if (session.isOpen()) {
            Query<String> queryObject = session.createQuery(ROLE_QUERY, String.class);
            queryObject.setParameter("user", user.getId());
            queryObject.setCacheable(true);
            queryObject.setCacheRegion(Constants.Cache.QUERY_ROLE);
            roles = queryObject.list();
            return roles;
        }
        return null;
    }

    @Override
    public boolean activated() {
        _User user = getUser();
        if (user == null) return false;
        return user.isActived();
    }

    public _UserSession getUserSession() {
        org.hibernate.Session session;
        if (getSessionFactory().isClosed()) {
            session = getSessionFactory().openSession();
        } else {
            session = getSessionFactory().getCurrentSession();
        }
        if (session.isOpen()) {
            Query queryObject = session.createQuery(USER_SESSION_QUERY, _UserSession.class);
            queryObject.setParameter("sessionId", sessionId);
            queryObject.setMaxResults(1);
            queryObject.setCacheable(true);
            queryObject.setCacheRegion("query.user_session");
            _UserSession object = (_UserSession) queryObject.uniqueResult();
            return object;
        }
        return null;
    }
}