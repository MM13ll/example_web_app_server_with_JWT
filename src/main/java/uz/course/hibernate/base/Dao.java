package uz.course.hibernate.base;

import org.hibernate.Session;
import uz.course.hibernate.domain._User;
import uz.course.to.CoreMap;
import uz.course.to.FilterParameters;
import uz.course.to.SelectItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by Virus on 19-Aug-16.
 */
public interface Dao<T extends _Entity> {

    T save(T entity);

    void delete(T entity);

    T get(Long id);

    T get(CoreMap item);

    T get(SelectItem item);

    Stream<T> list();

    Stream find(String query, Map<String, ?> params);

    List find(String query, Map<String, ?> params, String cacheName);

    Stream findInterval(String query, Map<String, ?> params, int offset, int limit);

    Stream findNativeInterval(String query, Class clazz, Map<String, ?> params, int offset, int limit);

    Object findSingle(String query, Map<String, ?> params);

    Object findSingleNative(String query, Map<String, ?> params);

    Object findSingle(String query, Map<String, ?> params, String cacheName);

    Session getSession();

    void updateManyToMany(List<T> list, List<Long> items);

    void deleteOneToMany(List<T> list, ArrayList<Long> items);

    Stream<T> list(FilterParameters filter);

    Integer total(FilterParameters filter);

    _User getUser();

    T getDefaultEmpty(Long id);

    void delete(Long id);
}