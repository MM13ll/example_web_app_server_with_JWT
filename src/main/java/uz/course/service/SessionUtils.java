package uz.course.service;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import uz.course.config.ApplicationContextProvider;
import uz.course.hibernate.domain._User;
import uz.course.to.RpcException;

import java.util.Date;
import java.util.List;

@Component
public abstract class SessionUtils {
    private static ThreadLocal<SessionUtils> instance = new ThreadLocal<SessionUtils>();
    private static Class serverSecurityContextClass = DefaultSessionContext.class;

    static {
        serverSecurityContextClass = SessionContext.class;
    }

    public static SessionUtils getInstance() {
        if (instance.get() == null) {
            createServerSecurityContext();
        }
        return instance.get();
    }

    private static void createServerSecurityContext() {
        try {
            SessionUtils context = (SessionUtils) serverSecurityContextClass.newInstance();
            instance.set(context);
            context.setStartDate(new Date().getTime());
        } catch (Throwable t) {
            throw new RpcException("Error creating SessionUtils instance");
        }
    }

    public abstract long getStartDate();

    public abstract void setStartDate(long startDate);

    public abstract String getSessionId();

    public abstract void setSessionId(String sessionId);

    public abstract boolean isLoggedIn();

    public SessionFactory getSessionFactory() {
        return (SessionFactory) ApplicationContextProvider.applicationContext.getBean("sessionFactory");
    }

    public abstract String getLanguage();

    public abstract void setLanguage(String language);

    public abstract _User getUser();

    public abstract List<String> getRoles();

    public abstract boolean activated();
}