package kz.reself.impl.service;

import kz.reself.impl.model.CrmRequest;
import kz.reself.impl.model.CrmResponseDto;
import kz.reself.impl.model.Production;
import kz.reself.impl.model.impl;

import java.util.List;

public interface IProductionService {

  Production getById(Long id);

  List<Production> getAll();

  void deleteById(Long id);

  Production create(Production production);

  void receiveResponse(CrmResponseDto crmResponseDto);

  impl createTemplate(impl impl);

  CrmRequest sendToQueqe(Long id, String quantity);
}
