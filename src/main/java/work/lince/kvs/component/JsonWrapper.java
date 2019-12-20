package work.lince.kvs.component;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class JsonWrapper {

    protected Gson gson;

    @PostConstruct
    public void init() {
        gson = new Gson();
    }

    public String toJson(Object value) {
        return gson.toJson(value);
    }

    public Map fromJson(String value) {
        return gson.fromJson(value, Map.class);
    }


}