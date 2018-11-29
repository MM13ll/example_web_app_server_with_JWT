package uz.course.hibernate.domain;

import uz.course.hibernate.base.Db;
import uz.course.hibernate.base._Entity;

import javax.persistence.*;
import java.time.Instant;

/**
 * Created by Virus on 19-Sep-16.
 */
@Entity
@Db
@Table(name = "user_session")
public class _UserSession extends _Entity {

    @org.hibernate.annotations.Index(name = "Index_user_session_session_id", columnNames = "session_id")
    @Column(unique = true)
    private String token;
    private String userAgent;
    private String IPAddress;
    private boolean expired = false;
    private Instant lastAccessTime;
    private Instant signiinDate;
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @org.hibernate.annotations.ForeignKey(name = "none")
    private _User user;
    @Column(insertable = false, updatable = false)
    private Long user_id;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public boolean getExpired() {
        return expired;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public Instant getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Instant lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public Instant getSigniinDate() {
        return signiinDate;
    }

    public void setSigniinDate(Instant signiinDate) {
        this.signiinDate = signiinDate;
    }

    public _User getUser() {
        return user;
    }

    public void setUser(_User user) {
        this.user = user;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String generatedToken) {
        this.uuid = generatedToken;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
