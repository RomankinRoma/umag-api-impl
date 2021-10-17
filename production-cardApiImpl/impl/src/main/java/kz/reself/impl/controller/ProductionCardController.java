package kz.reself.impl.controller;

import kz.reself.impl.model.impl;
import kz.reself.impl.service.IimplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/production/card")
public class implController {

  @Autowired
  private IimplService implService;

  @RequestMapping(value = "/v1/create", method = RequestMethod.POST)
  public ResponseEntity<?> create(@RequestBody impl impl) {
    return ResponseEntity.ok(implService.create(impl));
  }

  @RequestMapping(value = "/v1/delete/{id}", method = RequestMethod.DELETE)
  public void delete(@PathVariable(name = "id") Long id) {
    implService.deleteById(id);
  }

  @RequestMapping(value = "/v1/get/{id}", method = RequestMethod.GET)
  public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(implService.getById(id));
  }

  @RequestMapping(value = "/v1/get/all", method = RequestMethod.GET)
  public ResponseEntity<?> getAll() {
    return ResponseEntity.ok(implService.getAll());
  }

}
