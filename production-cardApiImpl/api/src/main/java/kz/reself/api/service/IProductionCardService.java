package kz.reself.api.service;

import kz.reself.api.model.impl;

import java.util.List;

public interface IimplService {

  impl getById(Long id);

  List<impl> getAll();

  void deleteById(Long id);

  impl create(impl impl);


}
