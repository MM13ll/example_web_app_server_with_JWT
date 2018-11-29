package uz.course.utils;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.codec.binary.Hex;
import org.springframework.web.util.WebUtils;
import uz.course.config.ApplicationContextProvider;
import uz.course.to.RpcException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class ServerUtils {
    public static Gson gson = new GsonBuilder().serializeNulls().setLenient().create();
    public static PropertyNamingStrategy.UpperCamelCaseStrategy camelCase = new PropertyNamingStrategy.UpperCamelCaseStrategy();
    public static HashMap<String, Class> beenTypeMap = new HashMap<>();
    public static HashMap<String, Object> beenMap = new HashMap<>();
    public static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static String TIME_FORMAT = "H : mm";// e.g 20180616;
    private static String SHORT_DATE_FORMAT = "dd-MM-yyyy";// e.g 20180616;
    private static String DD_MM_FORMAT = "dd - MMMM";
    private static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern(TIME_FORMAT);
    private static SimpleDateFormat shortDateFormat = new SimpleDateFormat(SHORT_DATE_FORMAT);
    private static DateTimeFormatter ddMMFormat = DateTimeFormatter.ofPattern(DD_MM_FORMAT);

    public static DateTimeFormatter getTimeFormat() {
        return timeFormat;
    }

    private static SimpleDateFormat getShortDateFormat() {
        return shortDateFormat;
    }

    public static DateTimeFormatter getddMMFormat() {
        return ddMMFormat;
    }

    public static String getTimeFormat(LocalTime date) {
        if (date == null) return null;
        return date.format(getTimeFormat());
    }

    public static String getShortDateFormat(Instant date) {
        if (date == null) return null;
        return getShortDateFormat().format(Date.from(date));
    }

    public static String getddMMFormat(LocalDate date) {
        if (date == null) return null;
        return getddMMFormat().format(date);
    }

    public static LocalTime getTimeParse(String date) {
        try {
            if (date == null || "".equals(date)) return null;
            return LocalTime.parse(date, getTimeFormat());
        } catch (Exception e) {
            throw new RpcException("Вақт форматини тўғри киритинг<br/> [HH : mm]");
        }
    }

    public static String encodeSHA512(String input, String key) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        input = input + key;
        byte[] inputBytes = new byte[0];
        try {
            inputBytes = input.getBytes("ASCII");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] digest = md.digest(inputBytes);
        StringBuilder builder = new StringBuilder();
        for (byte b : digest) {
            builder.append(Integer.toHexString(b & 256));
        }

        MessageDigest digest2 = null;
        try {
            digest2 = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = digest2.digest(
                input.getBytes(StandardCharsets.US_ASCII));
        String sha256hex = new String(new Hex().encode(hash));
        return sha256hex;
    }

    public static String getAccessToken(HttpServletRequest request) {
        Cookie accessToken = WebUtils.getCookie(request, "access_token");
        return accessToken == null ? null : accessToken.getValue();
    }

    public static Class getBeanType(String name) {
        if (beenTypeMap.containsKey(name))
            return beenTypeMap.get(name);
        beenTypeMap.put(name, getBean(name).getClass());
        return getBeanType(name);
    }

    public static Object getBean(String name) {
        if (beenMap.containsKey(name))
            return beenMap.get(name);
        Object service = ApplicationContextProvider.applicationContext.getBean(name);
        beenMap.put(name, service);
        return getBean(name);
    }
}