package kz.reself.api.service;

import kz.reself.api.model.CrmRequest;
import kz.reself.api.model.CrmResponseDto;
import kz.reself.api.model.Production;
import kz.reself.api.model.impl;

import java.util.List;

public interface IProductionService {

  Production getById(Long id);

  List<Production> getAll();

  void deleteById(Long id);

  Production create(Production api);

  void receiveResponse(CrmResponseDto crmResponseDto);

  impl createTemplate(impl apiCard);

  CrmRequest sendToQueqe(Long id, String quantity);
}
