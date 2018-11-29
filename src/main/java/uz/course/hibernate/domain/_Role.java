package uz.course.hibernate.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import uz.course.hibernate.base.Db;
import uz.course.hibernate.base._Item;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * Created by Virus on 25-Aug-16.
 */
@Entity
@Db
@Table(name = "role")
@Where(clause = "state <> 2")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class _Role extends _Item implements GrantedAuthority {

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + getCode();
    }
}