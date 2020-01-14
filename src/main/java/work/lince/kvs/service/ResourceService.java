package work.lince.kvs.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.lince.commons.authentication.AuthenticationService;
import work.lince.commons.exception.NotFoundException;
import work.lince.commons.persistence.model.LastChangeDetail;
import work.lince.kvs.model.Resource;
import work.lince.kvs.repository.ResourceRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ResourceService {

    @Autowired
    protected ResourceRepository repository;

    @Autowired
    protected AuthenticationService authenticationService;

    public Resource update(Long ttl, String name, String key, String value) {
        Optional<Resource> old = repository.findByNameAndKey(name, key);
        String uuid = old.isPresent() ? old.get().getId() : generateUuid();
        return saveOrUpdate(uuid, ttl, name, key, value);
    }

    protected String generateUuid() {
        return UUID.randomUUID().toString();
    }

    protected Resource saveOrUpdate(String uuid, Long ttl, String name, String key, String value) {
        LastChangeDetail detail = LastChangeDetail.builder()
                .date(ZonedDateTime.now())
                .user(authenticationService.getAuthenticatedUser())
                .build();
        Resource resource = Resource.builder()
                .id(uuid)
                .ttl(ttl)
                .name(name)
                .key(key)
                .value(value)
                .lastChange(detail)
                .build();
        return repository.save(resource);
    }

    public Resource findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException());
    }

    public Resource findByNameAndKey(String name, String key) {
        return repository.findByNameAndKey(name, key)
                .orElseThrow(() -> new NotFoundException());
    }

    public List<Resource> findByName(String name) {
        return repository.findByName(name);
    }

    public Long remove(String name, String key) {
        Long qtt = repository.deleteByNameAndKey(name, key);
        log.info("[processName:remove][name:{}][key:{}]Removed {}", name, key, qtt);
        return qtt;
    }

    public Long remove(String name) {
        Long qtt = repository.deleteByName(name);
        log.info("[processName:remove][name:{}]Removed {}", name, qtt);
        return qtt;
    }


}