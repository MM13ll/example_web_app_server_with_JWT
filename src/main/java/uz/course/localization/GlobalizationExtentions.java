package uz.course.localization;

import org.springframework.util.StringUtils;
import uz.course.config.ApplicationContextProvider;
import uz.course.hibernate.base._Entity;
import uz.course.hibernate.base._Item;
import uz.course.service.SessionUtils;
import uz.course.to.SelectItem;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Virus on 31-Aug-16.
 */
public class GlobalizationExtentions {

    public static final String UZL = "uzl";
    public static final String RU = "ru";
    public static final String UZ = "uz";
    private static final String[] months = {"январ", "феврал", "март", "апрел", "май", "июн", "июл", "август", "сентябр", "октябр", "ноябр", "декабр"};
    private static final String[] months_ru = {"январь", "февраль", "марть", "апрель", "май", "июнь", "июль", "августь", "сентябрь", "октябрь", "ноябрь", "декабрь"};

    private static GlobalizationExtentions instance;

    static {
        instance = new GlobalizationExtentions();
        instance.message = ApplicationContextProvider.applicationContext.getBean(Message.class);
    }

    private Message message;

    public static GlobalizationExtentions getInstance() {
        return instance;
    }

    public static String getSystemLanguage() {
        return SessionUtils.getInstance().getLanguage();
    }

    public static <T extends _Item> SelectItem getItem(T t) {
        if (t == null) return null;
        return new SelectItem(t.getId(), getName(t), getName(t), "" + t.getId());
    }

    public static <T extends _Item> String getName(T t) {
        if (t == null) return null;
        switch (getSystemLanguage()) {
            case UZL:
                return StringUtils.isEmpty(t.getName_uzl()) ? t.getName() : t.getName_uzl();
            case RU:
                return StringUtils.isEmpty(t.getName_ru()) ? t.getName() : t.getName_ru();
            default:
                return t.getName();
        }
    }

    public static <T extends _Item> String getByLn(T t, String name) {
        try {
            if (t == null) return null;
            String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
            switch (getSystemLanguage()) {
                case UZL: {
                    Method method = t.getClass().getMethod(methodName + UZL);
                    method.setAccessible(true);
                    String value = (String) method.invoke(t);
                    return StringUtils.isEmpty(value) ? value : value;
                }
                case RU: {
                    Method method = t.getClass().getMethod(methodName + RU);
                    method.setAccessible(true);
                    String value = (String) method.invoke(t);
                    return StringUtils.isEmpty(value) ? value : value;
                }
                default: {
                    Method method = t.getClass().getMethod(methodName + RU);
                    method.setAccessible(true);
                    String value = (String) method.invoke(t);
                    return StringUtils.isEmpty(value) ? value : value;
                }
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static <T extends _Item> void setName(T t, String name) {
        switch (getSystemLanguage()) {
            case UZL:
                t.setName_uzl(name);
                if (StringUtils.isEmpty(t.getName()))
                    t.setName(name);
                if (StringUtils.isEmpty(t.getName_ru()))
                    t.setName_ru(name);
                break;
            case RU:
                t.setName_ru(name);
                if (StringUtils.isEmpty(t.getName()))
                    t.setName(name);
                if (StringUtils.isEmpty(t.getName_uzl()))
                    t.setName_uzl(name);
                break;
            default: {
                t.setName(name);
                if (StringUtils.isEmpty(t.getName_ru()))
                    t.setName_ru(name);
                if (StringUtils.isEmpty(t.getName_uzl()))
                    t.setName_uzl(name);
                break;
            }
        }
    }

    public static <T extends Enum> SelectItem getItem(T t) {
        if (t == null) return null;
        return new SelectItem(instance.message.localize2(t.name(), t.name()), "" + t.name());
    }

    public static <T extends _Entity> String arrayToString(List<T> items) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        if (items != null)
            for (_Entity item : items) {
                if (i++ > 0)
                    stringBuilder.append(",<br/>");
                stringBuilder.append(item.toString());
            }
        return stringBuilder.toString();
    }

    public static <T extends Enum> ArrayList<SelectItem> getItems(T[] items) {
        ArrayList<SelectItem> list = new ArrayList<SelectItem>();
        for (T item : items) {
            list.add(getItem(item));
        }
        return list;
    }

    public static String getLanguagePrefix() {
        switch (getSystemLanguage()) {
            case UZ:
                return "";
            default:
                return "_" + getSystemLanguage();
        }
    }

    public static ArrayList<String> getMonths(List<String> number) {
        ArrayList<String> temp = new ArrayList<String>();
        switch (getSystemLanguage()) {
            case UZ: {
                number.stream().forEach(x -> temp.add(months[Integer.parseInt(x.trim()) - 1]));
            }
            break;
            default: {
                number.stream().forEach(x -> temp.add(months_ru[Integer.parseInt(x.trim()) - 1]));
            }
            break;
        }
        return temp;
    }

    public String getLocalizationField(String name) {
        switch (getSystemLanguage()) {
            case UZL:
                return String.format("%s_%s", name, UZL);
            case RU:
                return String.format("%s_%s", name, RU);
            default:
                return name;
        }
    }

    public String localization(String name, String name_uzl, String name_ru) {
        switch (GlobalizationExtentions.getSystemLanguage()) {
            case UZL:
                return StringUtils.isEmpty(name_uzl) ? name : name_uzl;
            case RU:
                return StringUtils.isEmpty(name_ru) ? name : name_ru;
            default:
                return name;
        }
    }
}