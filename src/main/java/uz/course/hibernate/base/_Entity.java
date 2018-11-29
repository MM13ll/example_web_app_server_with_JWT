package uz.course.hibernate.base;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.util.StringUtils;
import sun.reflect.misc.FieldUtil;
import sun.reflect.misc.MethodUtil;
import uz.course.to.CoreMap;
import uz.course.to.RpcException;
import uz.course.to.State;
import uz.course.utils.ServerUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@MappedSuperclass
public class _Entity implements Serializable, Cloneable {
    @Transient
    private static final Logger log = LogManager.getLogger(_Entity.class);
    @Transient
    public static HashMap<Class, List<Field>> fieldsMap = new HashMap<>();
    @Transient
    public static HashMap<Class, HashMap<String, Method>> methodsMap = new HashMap<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private State state = State.New;

    public static String getEntityName(_Entity o) {
        if (o instanceof HibernateProxy) {
            HibernateProxy proxy = (HibernateProxy) o;
            return proxy.getHibernateLazyInitializer().getEntityName();
        }
        Entity entity = o.getClass().getAnnotation(Entity.class);
        if (entity != null) {
            return !"".equals(entity.name()) ? entity.name() : o.getClass().getName();
        }
        return "";
    }

    public static List<Field> getFields(Class<?> clazz) {
        if (!fieldsMap.containsKey(clazz)) {
            Class<?> cls = clazz;
            List<Field> fields = new ArrayList<>();
            HashMap<String, Method> methods = new HashMap<>();
            while (!_Entity.class.equals(cls)) {
                fields.addAll(Arrays.asList(FieldUtil.getDeclaredFields(cls)));
                Arrays.stream(MethodUtil.getPublicMethods(cls)).forEach(method -> methods.put(method.getName(), method));
                cls = cls.getSuperclass();
            }
            fieldsMap.put(clazz, fields);
            methodsMap.put(clazz, methods);
        }
        return fieldsMap.get(clazz);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof _Entity)) {
            return false;
        }
        if (getId() == null) {
            return false;
        }

        _Entity unObject = (_Entity) o;

        String table1 = getEntityName(this);
        String table2 = getEntityName(unObject);
        return !(table1 == null || !table1.equals(table2)) && getId().equals(unObject.getId());
    }

    @SuppressWarnings("unchecked")
    public <T extends _Entity> T cloneShallow() {
        T clone = null;
        try {
            clone = (T) super.clone();
            clone = getShallowClonedObject(clone);
        } catch (CloneNotSupportedException e) {
//            log.error(e);
//            e.printStackTrace();
        }
        return clone;
    }

    protected <T extends _Entity> T getShallowClonedObject(T obj) {
        Class cls = obj.getClass();
        while (cls != null) {
            for (Field f : cls.getDeclaredFields()) {
                f.setAccessible(true);
                if (Collection.class.isAssignableFrom(f.getType())) {
                    try {
                        Object o = f.get(obj);
                        if (o != null) {
                            try {
                                Object newInstance = o.getClass().newInstance();
                                f.set(obj, newInstance);
                            } catch (InstantiationException e) {
//                                log.error(e);
//                                e.printStackTrace();
                            }
                        }
                    } catch (IllegalAccessException e) {
//                        log.error(e);
//                        e.printStackTrace();
                    }
                }
                if (f.getName().toLowerCase().equals("id")) {
                    try {
                        f.set(obj, null);
                    } catch (IllegalAccessException e) {
//                        log.error(e);
//                        e.printStackTrace();
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        return obj;
    }

    public Boolean isNew() {
        return getId() == null;
    }

    public String toString() {
        return "[id=" + getId() + "]";
    }

    public int hashCode() {
        return (getId() != null ? getId().hashCode() : 0);
    }

    public CoreMap getMap() throws RpcException {
        return getMap(false);
    }

    public void setMap(CoreMap map) throws RpcException {
        try {
            for (Field field : getFields(this.getClass())) {
                if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) continue;
                field.setAccessible(true);
                switch (field.getType().getSimpleName()) {
                    case "String": {
                        field.set(this, map.getString(field.getName()));
                    }
                    break;
                    case "Double": {
                        field.set(this, map.getDouble(field.getName()));
                    }
                    break;
                    case "double": {
                        field.set(this, map.get_double(field.getName()));
                    }
                    break;
                    case "Boolean": {
                        field.set(this, map.getBoolean(field.getName()));
                    }
                    break;
                    case "boolean": {
                        field.set(this, map.getBool(field.getName()));
                    }
                    break;
                    case "Integer": {
                        field.set(this, map.getInteger(field.getName()));
                    }
                    break;
                    case "int": {
                        field.set(this, map.getInteger(field.getName()));
                    }
                    break;
                    case "Long": {
                        field.set(this, map.getLong(field.getName()));
                    }
                    break;
                    case "long": {
                        field.set(this, map.get_long(field.getName()));
                    }
                    break;
                    case "Date": {
                        field.set(this, map.getDate(field.getName()));
                    }
                    break;
                    case "Instant": {
                        Date date = map.getDate(field.getName());
                        if (date == null)
                            field.set(this, null);
                        else
                            field.set(this, date.toInstant());
                    }
                    break;
                    case "LocalTime": {
                        if (!StringUtils.isEmpty(map.getString(field.getName()))) {
                            LocalTime time = LocalTime.parse(map.getString(field.getName()));
                            field.set(this, time);
                        } else
                            field.set(this, null);
                    }
                    break;
                    case "LocalDate": {
                        if (!StringUtils.isEmpty(map.getString(field.getName()))) {
                            LocalDate time = LocalDate.parse(map.getString(field.getName()), ServerUtils.dateFormat);
                            field.set(this, time);
                        } else
                            field.set(this, null);
                    }
                    break;
                    default: {
                        if (field.getType().isArray() || Collection.class.isAssignableFrom(field.getType()) || Map.class.isAssignableFrom(field.getType())) {
                            continue;
                        } else
                            log.error(field.getType());
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RpcException(e.getMessage());
        }
    }

    public CoreMap getMap(boolean deeply) throws RpcException {
        CoreMap map = new CoreMap();
        try {
            for (Field field : getFields(this.getClass())) {
                if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) continue;
                field.setAccessible(true);

                Object value = field.get(this);
                if (value == null) {
                    map.addString(field.getName(), null);
                    continue;
                }

                switch (field.getType().getSimpleName()) {
                    case "String":
                    case "Boolean":
                    case "boolean": {
                        map.addString(field.getName(), String.valueOf(value));
                    }
                    break;
                    case "Double":
                    case "double":
                    case "Integer":
                    case "int":
                    case "Long":
                    case "long": {
                        map.addString(field.getName(), String.valueOf(value));
                    }
                    break;
                    case "Date": {
                            map.addDate(field.getName(), (Date) value);
                    }
                    break;
                    case "Instant": {
                            map.addDate(field.getName(), Date.from(((Instant) value)));
                    }
                    break;
                    case "LocalTime": {
                            map.addString(field.getName(), ((LocalTime) value).format(ServerUtils.getTimeFormat())/* value.toString()*/);
                    }
                    break;
                    case "LocalDate": {
                            map.addString(field.getName(), ((LocalDate) value).format(ServerUtils.dateFormat));
                    }
                    break;
                    default: {
                        if (deeply && _Entity.class.isAssignableFrom(field.getType())) {
                            map.addString(field.getName(), ((_Entity) value).getId().toString());
                        } else if (deeply && Collection.class.isAssignableFrom(field.getType())) {
//                            map.addStrings(field.getName(), ((Collection<_Entity>) value).stream().map(entity -> "" + entity.getId()).collect(Collectors.toList()));
                            String methodName = String.format("get%s", ServerUtils.camelCase.translate(field.getName()));
                            if (methodsMap.get(this.getClass()).containsKey(methodName)) {
                                Method method = methodsMap.get(this.getClass()).get(methodName);
                                Object data = method.invoke(this);
                                map.addStrings(field.getName(), ((Collection<_Entity>) data).stream().map(entity -> "" + entity.getId()).collect(Collectors.toList()));
                            }
                        } else if (field.getType().isArray() || Map.class.isAssignableFrom(field.getType())) {
                            continue;
                        } else
                            log.error(field.getType());

                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RpcException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(e.getMessage());
        } finally {
            map.setId(this.getId());
            map.setState(this.getState());
            map.addString("id", "" + id);
            return map;
        }
    }
}