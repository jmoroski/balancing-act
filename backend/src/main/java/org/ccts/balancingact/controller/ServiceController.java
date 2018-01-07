package org.ccts.balancingact.controller;

import java.util.List;
import java.util.UUID;

import org.ccts.balancingact.dao.ServiceDao;
import org.ccts.balancingact.model.api.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/services")
public class ServiceController {
    @Autowired
    private ServiceDao serviceDao;

    @GetMapping
    public ResponseEntity<List<Service>> getService() {
        return new ResponseEntity<>(serviceDao.getServices(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Service> addService(@RequestBody final Service service) {
        return new ResponseEntity<>(this.serviceDao.addService(service), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Service> updateService(@RequestBody final Service service) {
        return new ResponseEntity<>(this.serviceDao.updateService(service), HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteService(@PathVariable UUID id) {
        this.serviceDao.removeService(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
