package kz.reself.impl.service;

import kz.reself.impl.model.ProdCardItem;

import java.util.List;

public interface IProdCardItemService {

  ProdCardItem getById(Long id);

  List<ProdCardItem> getAll();

  void deleteById(Long id);

  ProdCardItem create(ProdCardItem prodCardItem);

  List<ProdCardItem> saveAll(List<ProdCardItem> prodCardItem);

}
