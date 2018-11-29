package uz.course.service;


import uz.course.hibernate.domain._User;

import java.util.List;

/**
 * Created by Virus on 2016/12/02.
 */
public class DefaultSessionContext extends SessionUtils {

    private long startDate;
    private String sessionId;

    @Override
    public long getStartDate() {
        return 0;
    }

    @Override
    public void setStartDate(long startDate) {
    }

    @Override
    public String getSessionId() {
        return null;
    }

    @Override
    public void setSessionId(String sessionId) {

    }

    @Override
    public boolean isLoggedIn() {
        return false;
    }

    @Override
    public String getLanguage() {
        return "en";
    }

    @Override
    public void setLanguage(String language) {
    }

    @Override
    public _User getUser() {
        return null;
    }

    @Override
    public List<String> getRoles() {
        return null;
    }

    @Override
    public boolean activated() {
        return false;
    }
}