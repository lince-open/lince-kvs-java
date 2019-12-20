package work.lince.kvs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import work.lince.kvs.model.Resource;
import work.lince.kvs.service.JsonWrapperService;
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
    protected JsonWrapperService json;

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