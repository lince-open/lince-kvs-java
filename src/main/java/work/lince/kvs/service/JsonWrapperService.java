package work.lince.kvs.service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import work.lince.commons.authentication.AuthenticationService;
import work.lince.commons.exception.NotFoundException;
import work.lince.commons.persistence.model.LastChangeDetail;
import work.lince.kvs.model.Resource;
import work.lince.kvs.repository.ResourceRepository;

import javax.annotation.PostConstruct;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class JsonWrapperService {

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