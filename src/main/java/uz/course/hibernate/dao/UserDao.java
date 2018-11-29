package uz.course.hibernate.dao;

import uz.course.hibernate.base.Dao;
import uz.course.hibernate.domain._User;

public interface UserDao extends Dao<_User> {
    _User findByPhone(String phone);

    boolean existsByPhone(String phone);

}