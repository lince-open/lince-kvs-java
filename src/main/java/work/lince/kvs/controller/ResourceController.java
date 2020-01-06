package work.lince.kvs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import work.lince.kvs.component.JsonWrapper;
import work.lince.kvs.model.Resource;
import work.lince.kvs.service.ResourceService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/resources", produces = MediaType.APPLICATION_JSON_VALUE)
public class ResourceController {

    @Autowired
    protected ResourceService service;

    @Autowired
    protected JsonWrapper json;

    @PutMapping(path = "/{resourceName}/{resourceId}")
    public ResponseEntity<String> update(@RequestHeader(value = "ttl", required = false) Optional<Long> ttl,
                                         @PathVariable("resourceName") final String resourceName,
                                         @PathVariable("resourceId") final String resourceId,
                                         @RequestBody String value) {
        Resource resource = service.update(ttl.orElse(0L), resourceName, resourceId, value);
        return ResponseEntity
                .ok()
                .header("uuid", resource.getId())
                .body(resource.getValue());
    }

    @PostMapping(path = "/{resourceName}/{resourceId}")
    public ResponseEntity<String> create(@RequestHeader(value = "ttl", required = false) Optional<Long> ttl,
                                         @PathVariable("resourceName") final String resourceName,
                                         @PathVariable("resourceId") final String resourceId,
                                         @RequestBody String value) {
        Resource resource = service.update(ttl.orElse(0L), resourceName, resourceId, value);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("uuid", resource.getId())
                .body(resource.getValue());
    }

    @GetMapping(path = "/{resourceName}/{resourceId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> findByNameAndId(@PathVariable("resourceName") final String resourceName,
                                                  @PathVariable("resourceId") final String resourceId) {
        Resource resource = service.findByNameAndKey(resourceName, resourceId);
        return ResponseEntity
                .ok()
                .header("uuid", resource.getId())
                .body(resource.getValue());
    }

    @GetMapping(path = "/{resourceName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Map> findByName(@PathVariable("resourceName") final String resourceName) {
        return service.findByName(resourceName).stream().map(resource ->
                json.fromJson(resource.getValue())
        ).collect(Collectors.toList());
    }

    @DeleteMapping(path = "/{resourceName}/{resourceId}")
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable("resourceName") final String resourceName,
                       @PathVariable("resourceId") final String resourceId) {
        service.remove(resourceName, resourceId);
    }

    @DeleteMapping(path = "/{resourceName}")
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable("resourceName") final String resourceName) {
        service.remove(resourceName);
    }

}