package uz.course.hibernate.dao;

import uz.course.hibernate.base.Dao;
import uz.course.hibernate.domain._Role;
import uz.course.to.SelectItem;

import java.util.List;
import java.util.stream.Stream;

public interface RoleDao extends Dao<_Role> {
    Stream<_Role> findByUserName(String userName);

    List<SelectItem> getItems();

    _Role getByCode(String code);
}
