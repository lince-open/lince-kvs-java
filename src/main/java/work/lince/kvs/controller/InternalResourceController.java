package work.lince.kvs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import work.lince.kvs.model.Resource;
import work.lince.kvs.service.ResourceService;

@RestController
@RequestMapping(path = "/internal/resources", produces = MediaType.APPLICATION_JSON_VALUE)
public class InternalResourceController {

    @Autowired
    protected ResourceService service;

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Resource findById(@PathVariable("id") final String id) {
        return service.findById(id);
    }

}