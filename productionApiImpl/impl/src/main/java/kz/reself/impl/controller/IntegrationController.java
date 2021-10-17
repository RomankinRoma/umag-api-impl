package kz.reself.impl.controller;

import feign.Param;
import kz.reself.impl.model.integration.Debit;
import kz.reself.impl.model.integration.Decomission;
import kz.reself.impl.service.IIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/tredo")
public class IntegrationController {

  @Autowired
  private IIntegrationService integrationService;

  @RequestMapping(value = "/get/all/products/pageable", method = RequestMethod.GET)
  public ResponseEntity<?> getAllProducts(@RequestParam Map<String, String> params) {
    return ResponseEntity.ok(integrationService.getAllProducts(params));
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ResponseEntity<?> login() {
    return ResponseEntity.ok(integrationService.login());
  }

  @RequestMapping(value = "/get/dec/debit/pageable", method = RequestMethod.GET)
  public ResponseEntity<?> getAllDecDebits(@RequestParam Map<String, String> params) {
    return ResponseEntity.ok(integrationService.getDecomisionDebitByStoreId(params));
  }

  @RequestMapping(value = "/get/product/{barcode}", method = RequestMethod.GET)
  public ResponseEntity<?> getProductByBarcode(@PathVariable String barcode,
                                               @Param String storeId) {
    return ResponseEntity.ok(integrationService.getProductById(barcode, storeId));
  }

  @RequestMapping(value = "/get/debit/{id}", method = RequestMethod.GET)
  public ResponseEntity<?> getDebitById(@PathVariable String id, @Param String storeId) {
    return ResponseEntity.ok(integrationService.getDebitByStoreId(id, storeId));
  }

  @RequestMapping(value = "/get/decomission/{id}", method = RequestMethod.GET)
  public ResponseEntity<?> getDecomissionById(@PathVariable String id, @Param String storeId) {
    return ResponseEntity.ok(integrationService.getDecomisionByStoreId(id, storeId));
  }

  @RequestMapping(value = "/create/debit", method = RequestMethod.POST)
  public ResponseEntity<?> createDebit(@RequestBody Debit debit, @Param String storeId) {
    return ResponseEntity.ok(integrationService.createDebit(debit, storeId));
  }

  @RequestMapping(value = "/delete/debit/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteDebit(@PathVariable Long id, @Param String storeId) {
    return ResponseEntity.ok(integrationService.deleteDebit(id, storeId));
  }

  @RequestMapping(value = "/create/decomission", method = RequestMethod.POST)
  public ResponseEntity<?> createDecomission(@RequestBody Decomission decomission,
                                             @Param String storeId) {
    return ResponseEntity.ok(integrationService.createDecommission(decomission, storeId));
  }

  @RequestMapping(value = "/delete/decomission/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteDecomission(@PathVariable Long id, @Param String storeId) {
    return ResponseEntity.ok(integrationService.deleteDecomission(id, storeId));
  }

}
