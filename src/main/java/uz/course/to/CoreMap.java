package uz.course.to;


import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class CoreMap extends AbstractMap implements Serializable {
    private HashMap<String, String> map = new LinkedHashMap<>();
    private HashMap<String, List<String>> map2 = new LinkedHashMap<String, List<String>>();
    private Long id;
    private State state;

    public CoreMap(Long id) {
        this.id = id;
    }

    public CoreMap() {
    }

    public Long getId() {
        return id;
    }

    public CoreMap setId(Long id) {
        this.id = id;
        return this;
    }

    public CoreMap add(String key, String value) {
        addString(key, value);
        return this;
    }

    public CoreMap add(String key, Long value) {
        addLong(key, value);
        return this;
    }

    @Override
    public HashMap<String, String> getInstance() {
        return map;
    }

    public HashMap<String, List<String>> getInstance2() {
        return map2;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}