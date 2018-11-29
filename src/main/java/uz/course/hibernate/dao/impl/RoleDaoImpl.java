package uz.course.hibernate.dao.impl;

import org.springframework.stereotype.Repository;
import uz.course.hibernate.base.DaoImpl;
import uz.course.hibernate.dao.RoleDao;
import uz.course.hibernate.domain._Role;
import uz.course.to.Constants;
import uz.course.to.SelectItem;

import java.util.List;
import java.util.stream.Stream;

@Repository(value = "roleDao")
public class RoleDaoImpl extends DaoImpl<_Role> implements RoleDao {
    public RoleDaoImpl() {
        super(_Role.class);
    }

    @Override
    public Stream<_Role> findByUserName(String userName) {
        return find("select r from _User t join t.roles r where t.phone = :user",
                preparing(new Entry("user", userName)));
    }

    @Override
    public List<SelectItem> getItems() {
        return find("select new uz.course.gwt.core.client.rpc.SelectItem(t.id,t.name,''||t.id)  from _Role t where t.state <> 2 order by t.name ",
                preparing(), Constants.Cache.QUERY_ROLE);
//        return list().map(role -> role.getSelectItem()).collect(Collectors.toList());
    }

    @Override
    public _Role getByCode(String code) {
        return (_Role) findSingle("select t from _Role t where t.code = :code and t.state <> 2 ",
                preparing(new Entry("code", code)), Constants.Cache.QUERY_ROLE);
    }
}

