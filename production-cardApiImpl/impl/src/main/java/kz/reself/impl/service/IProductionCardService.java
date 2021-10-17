package kz.reself.impl.service;

import kz.reself.impl.model.impl;

import java.util.List;

public interface IimplService {

  impl getById(Long id);

  List<impl> getAll();

  void deleteById(Long id);

  impl create(impl impl);


}
