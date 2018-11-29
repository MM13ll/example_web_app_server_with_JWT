package uz.course.hibernate.dao.impl;

import org.springframework.stereotype.Repository;
import uz.course.hibernate.base.DaoImpl;
import uz.course.hibernate.dao.UserDao;
import uz.course.hibernate.domain._User;
import uz.course.to.Constants;

@Repository(value = "userDao")
public class UserDaoImpl extends DaoImpl<_User> implements UserDao {
    public UserDaoImpl() {
        super(_User.class);
    }

    @Override
    public _User findByPhone(String phone) {
        return (_User) findSingle("select t from _User t where t.phone = :user and t.state <> 2 ",
                preparing(new Entry("user", phone)), Constants.Cache.QUERY_USER);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return ((Long) findSingle("select count(t) from _User t where t.phone = :phone and t.actived = true and t.state <> 2",
                preparing(new Entry("phone", phone)), Constants.Cache.QUERY_USER)) > 0;
    }

}
