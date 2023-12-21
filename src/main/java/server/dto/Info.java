package server.dto;

import java.util.HashMap;
import java.util.Map;

public class Info {
    private Long count;
    private Map<String, String> data = new HashMap<>();

    public Long getCount() {
        return count;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Info(){

    }
}
