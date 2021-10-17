package kz.reself.impl.controller;

import feign.Param;
import kz.reself.impl.model.Production;
import kz.reself.impl.model.impl;
import kz.reself.impl.service.IProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/production")
public class ProductionController {

  @Autowired
  private IProductionService productionServiceStub;

  @RequestMapping(value = "/v1/create", method = RequestMethod.POST)
  public ResponseEntity<?> create(@RequestBody Production production) {
    return ResponseEntity.ok(productionServiceStub.create(production));
  }

  @RequestMapping(value = "/v1/create/card", method = RequestMethod.POST)
  public ResponseEntity<?> create(@RequestBody impl impl) {
    return ResponseEntity.ok(productionServiceStub.createTemplate(impl));
  }

  @RequestMapping(value = "/v1/delete/{id}", method = RequestMethod.DELETE)
  public void delete(@PathVariable(name = "id") Long id) {
    productionServiceStub.deleteById(id);
  }

  @RequestMapping(value = "/v1/get/{id}", method = RequestMethod.GET)
  public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(productionServiceStub.getById(id));
  }

  @RequestMapping(value = "/v1/get/all", method = RequestMethod.GET)
  public ResponseEntity<?> getAll() {
    return ResponseEntity.ok(productionServiceStub.getAll());
  }

  @RequestMapping(value = "/v1/send/{id}", method = RequestMethod.GET)
  public ResponseEntity<?> send(@PathVariable Long id, @Param String quantity) {
    return ResponseEntity.ok(productionServiceStub.sendToQueqe(id, quantity));
  }

}
