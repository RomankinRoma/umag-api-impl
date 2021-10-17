package kz.reself.impl.controller;

import kz.reself.impl.model.ProdCardItem;
import kz.reself.impl.service.IProdCardItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/prod/card/item")
public class ProdCardItemController {

  @Autowired
  private IProdCardItemService prodCardItemService;

  @RequestMapping(value = "/v1/create",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> create(@RequestBody ProdCardItem prodCardItem) {
    return ResponseEntity.ok(prodCardItemService.create(prodCardItem));
  }

  @RequestMapping(value = "/v1/save/all",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> saveAll(@RequestBody List<ProdCardItem> prodCardItem) {
    return ResponseEntity.ok(prodCardItemService.saveAll(prodCardItem));
  }

  @RequestMapping(value = "/v1/delete/{id}",
      method = RequestMethod.DELETE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public void delete(@PathVariable(name = "id") Long id) {
    prodCardItemService.deleteById(id);
  }

  @RequestMapping(value = "/v1/get/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(prodCardItemService.getById(id));
  }

  @RequestMapping(value = "/v1/get",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getAll() {
    return ResponseEntity.ok(prodCardItemService.getAll());
  }

}
