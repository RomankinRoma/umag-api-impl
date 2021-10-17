package kz.reself.impl.service.impl;

import kz.reself.impl.model.impl;
import kz.reself.impl.repository.implRepo;
import kz.reself.impl.service.IimplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class implServiceImpl implements IimplService {

  @Autowired
  private implRepo implRepo;

  @Override
  public impl getById(Long id) {
    return implRepo.findById(id).get();
  }

  @Override
  public List<impl> getAll() {
    return implRepo.findAll();
  }

  @Override
  public void deleteById(Long id) {
    implRepo.deleteById(id);
  }

  @Override
  public impl create(impl impl) {
    return implRepo.saveAndFlush(impl);
  }

}
