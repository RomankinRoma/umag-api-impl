package kz.reself.api.service;

import kz.reself.api.model.ProdCardItem;

import java.util.List;

public interface IProdCardItemService {

  ProdCardItem getById(Long id);

  List<ProdCardItem> getAll();

  void deleteById(Long id);

  ProdCardItem create(ProdCardItem prodCardItem);

  List<ProdCardItem> saveAll(List<ProdCardItem> prodCardItem);

}
