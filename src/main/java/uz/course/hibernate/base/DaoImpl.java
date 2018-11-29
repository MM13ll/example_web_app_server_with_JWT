package uz.course.hibernate.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import uz.course.hibernate.domain._User;
import uz.course.service.SessionUtils;
import uz.course.to.CoreMap;
import uz.course.to.FilterParameters;
import uz.course.to.SelectItem;
import uz.course.to.State;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Virus on 19-Aug-16.
 */
public class DaoImpl<T extends _Entity> implements Dao<T> {

    private static final Logger log = LogManager.getLogger(DaoImpl.class);
    private final Class<T> clazz;
    @Autowired
    private SessionFactory sessionFactory;

    public DaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T save(T entity) {
        if (entity == null) return null;
        List<Field> fields = _Entity.getFields(clazz);
        Optional<Field> optional = fields.stream().filter(x -> _AuditInfo.class.equals(x.getType())).findFirst();
        if (optional.isPresent()) {
            Field field = optional.get();
            field.setAccessible(true);
            try {
                _AuditInfo auditInfo = (_AuditInfo) field.get(entity);
                if (auditInfo == null) {
                    auditInfo = new _AuditInfo();
                    field.set(entity, auditInfo);
                }
                if (auditInfo.getCreatedByUser() == null) {
                    auditInfo.setCreatedByUser(SessionUtils.getInstance().getUser());
                    auditInfo.setCreationDate(Instant.now());
                }
                auditInfo.setUpdatedByUser(SessionUtils.getInstance().getUser());
                auditInfo.setUpdatedDate(Instant.now());
            } catch (IllegalAccessException e) {
                log.error(e);
//                e.printStackTrace();
            }
        }
        if (entity.isNew())
            entity.setState(State.New);
        else if (entity.getState().ordinal() < State.Updated.ordinal())
            entity.setState(State.Updated);
        getSession().saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(T entity) {
        if (entity == null) return;
        entity.setState(State.Deleted);
        save(entity);
    }

    @Override
    public T get(Long id) {
        if (id == null) return null;
        return getSession().get(clazz, id);
    }

    @Override
    public T get(CoreMap item) {
        if (item == null) return null;
        if (item.getId() != null) return get(item.getId());
        return null;
    }

    @Override
    public T get(SelectItem item) {
        if (item == null) return null;
        if (item.getId() != null) return get(item.getId());
        if (!StringUtils.isEmpty(item.getValue())) return get(Long.parseLong(item.getValue()));
        return null;
    }

    @Override
    public Stream<T> list() {
        String query = "SELECT t FROM " + clazz.getSimpleName() + " t where t.state <> 2 ";
        try {
            if (clazz.newInstance() instanceof _Item) {
                query += " ORDER BY t.name ";
            }
        } catch (InstantiationException e) {
            log.error(e);
//            e.printStackTrace();
        } catch (IllegalAccessException e) {
            log.error(e);
//            e.printStackTrace();
        }
        return getSession().createQuery(query).stream();
    }

    @Override
    public Stream find(String query, Map<String, ?> params) {
        Query queryObject = getSession().createQuery(query);
        if (params != null) {
            for (Map.Entry<String, ?> entry : params.entrySet()) {
                queryObject.setParameter(entry.getKey(), entry.getValue());
            }
        }

        return queryObject.stream();
    }

    @Override
    public List find(String query, Map<String, ?> params, String cacheName) {
        Query queryObject = getSession().createQuery(query);
        if (params != null) {
            for (Map.Entry<String, ?> entry : params.entrySet()) {
                queryObject.setParameter(entry.getKey(), entry.getValue());
            }
        }
        queryObject.setCacheable(true);
        queryObject.setCacheRegion(cacheName);

        return queryObject.list();
    }

    @Override
    public Stream findInterval(String query, Map<String, ?> params, int offset, int limit) {
        Query queryObject = getSession().createQuery(query);
        if (params != null) {
            for (Map.Entry<String, ?> entry : params.entrySet()) {
                queryObject.setParameter(entry.getKey(), entry.getValue());
            }
        }

        if (offset != 0) {
            queryObject.setFirstResult(offset);
        }

        if (limit != 0) {
            queryObject.setMaxResults(limit);
        }

        return queryObject.stream();
    }

    @Override
    public Stream findNativeInterval(String query, Class clazz, Map<String, ?> params, int offset, int limit) {
        Query queryObject = getSession().createNativeQuery(query, clazz);
        if (params != null) {
            for (Map.Entry<String, ?> entry : params.entrySet()) {
                queryObject.setParameter(entry.getKey(), entry.getValue());
            }
        }

        if (offset != 0) {
            queryObject.setFirstResult(offset);
        }

        if (limit != 0) {
            queryObject.setMaxResults(limit);
        }
        return queryObject.stream();
    }

    @Override
    public Object findSingle(String query, Map<String, ?> params) {
        Query queryObject = getSession().createQuery(query);
        if (params != null) {
            for (Map.Entry<String, ?> entry : params.entrySet()) {
                queryObject.setParameter(entry.getKey(), entry.getValue());
            }
        }

        queryObject.setMaxResults(1);
        List list = queryObject.getResultList();
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    @Override
    public Object findSingleNative(String query, Map<String, ?> params) {
        Query queryObject = getSession().createNativeQuery(query);
        if (params != null) {
            for (Map.Entry<String, ?> entry : params.entrySet()) {
                queryObject.setParameter(entry.getKey(), entry.getValue());
            }
        }

        queryObject.setMaxResults(1);
        List list = queryObject.getResultList();
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    @Override
    public Object findSingle(String query, Map<String, ?> params, String cacheName) {
        Query queryObject = getSession().createQuery(query);
        if (params != null) {
            for (Map.Entry<String, ?> entry : params.entrySet()) {
                queryObject.setParameter(entry.getKey(), entry.getValue());
            }
        }

        queryObject.setMaxResults(1);
        queryObject.setCacheable(true);
        queryObject.setCacheRegion(cacheName);

        List list = queryObject.getResultList();
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    private Set<String> getAllFields(final Class<?> type) {
        Set<String> fields = new HashSet<String>();
        for (Field field : type.getDeclaredFields()) {
            fields.add(field.getName());
        }

        if (type.getSuperclass() != null) {
            fields.addAll(getAllFields(type.getSuperclass()));
        }
        return fields;
    }

    private boolean hasProperty(final String propertyName) {
        Set<String> properties = getAllFields(clazz.getClass());
        if (properties.contains(propertyName)) {
            return true;
        }
        return false;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Map<String, Object> preparing(Entry... params) {
        Map<String, Object> map = new HashMap<>();
        for (Map.Entry<String, Object> item : params) {
            if (item == null) continue;
            map.put(item.getKey(), item.getValue());
        }
        return map;
    }

    public void updateManyToMany(List<T> list, List<Long> items) {
        if (items == null) return;
        for (Long item : items) {
            Optional<T> query = list.stream().filter(x -> item != null && x.getId().equals(item)).findFirst();
            if (!query.isPresent()) {
                T entity = get(item);
                list.add(entity);
            }
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            T entity = list.get(i);
            Optional<Long> query = items.stream().filter(x -> x.equals(entity.getId())).findFirst();
            if (!query.isPresent()) {
                list.remove(entity);
            }
        }
    }

    @Override
    public void deleteOneToMany(List<T> list, ArrayList<Long> items) {
        List<Long> ids = new ArrayList<Long>();
        items.forEach(item -> {
            if (!(item == null || item == null))
                ids.add(item);
        });
        for (int i = list.size() - 1; i > -1; i--) {
            T entity = list.get(i);
            if (!(entity == null || ids.contains(entity.getId()))) {
                delete(entity);
                list.remove(entity);
            }
        }
    }

    @Override
    public Stream<T> list(FilterParameters filter) {
        String query = "SELECT t FROM " + clazz.getSimpleName() + " t ";
        T temp = null;
        try {
            temp = clazz.newInstance();
        } catch (Exception e) {
            log.error(e);
        }
        query += " WHERE state <> 2 ";
        Map<String, Object> preparing = preparing();
        if (temp instanceof _Item && !StringUtils.isEmpty(filter.getSearchKey())) {
            query += " and (lower(t.name) like :key or lower(t.name_ru) like :key or lower(t.name_uzl) like :key)";
            preparing.put("key", filter.getSearchQuery());
        }
        query+=" order by id desc";

        return findInterval(query, preparing, filter.getStart(), filter.getSize());
    }

    @Override
    public Integer total(FilterParameters filter) {
        String query = "SELECT count(t) FROM " + clazz.getSimpleName() + " t ";
        T temp = null;
        try {
            temp = clazz.newInstance();
        } catch (Exception e) {
            log.error(e);
        }
        query += " WHERE state <> 2 ";
        Map<String, Object> preparing = preparing();
        if (temp instanceof _Item && !StringUtils.isEmpty(filter.getSearchKey())) {
            query += " and (lower(t.name) like :key or lower(t.name_ru) like :key or lower(t.name_uzl) like :key)";
            preparing.put("key", filter.getSearchQuery());
        }

        return ((Long) findSingle(query,
                preparing)).intValue();
    }

    @Override
    public _User getUser() {
        return SessionUtils.getInstance().getUser();
    }

    @Override
    public T getDefaultEmpty(Long id) {
        T t = get(id);
        try {
            return t == null ? (T) clazz.newInstance() : t;
        } catch (InstantiationException e) {
            log.error(e);
//            e.printStackTrace();
        } catch (IllegalAccessException e) {
            log.error(e);
//            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        T obj = get(id);
        if (obj != null)
            getSession().delete(obj);
    }

//    protected String getNameField() {
//        return getField("name");
//    }

//    protected String getField(String name) {
//        return GlobalizationExtentions.getInstance().getLocalizationField(name);
//    }

    protected String searchByName(String tableName) {
        return "(lower(" + tableName + ".name) like :key or lower(" +
                tableName + ".name_ru) like :key or lower(" + tableName +
                ".name_uzl) like :key)";
    }

    protected static class Entry implements Map.Entry {
        private String key;
        private Object value;

        public Entry(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public Object getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public Object setValue(Object value) {
            return this.value = value;
        }
    }
}