package kz.reself.impl.feign;

import kz.reself.impl.model.Production;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@FeignClient(name = "production", url = "${prod.url}")
public interface ProductionClient {

  @RequestMapping(value = "/v1/create", method = RequestMethod.POST)
  ResponseEntity<?> create(@RequestBody Production production);

  @RequestMapping(value = "/v1/delete/{id}", method = RequestMethod.DELETE)
  void delete(@PathVariable(name = "id") Long id);

  @RequestMapping(value = "/v1/get/{id}", method = RequestMethod.GET)
  ResponseEntity<?> getById(@PathVariable(name = "id") Long id);

  @RequestMapping(value = "/v1/get/all", method = RequestMethod.GET)
  ResponseEntity<?> getAll();
}
