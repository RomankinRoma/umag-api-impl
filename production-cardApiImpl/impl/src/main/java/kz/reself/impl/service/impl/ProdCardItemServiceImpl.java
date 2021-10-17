package kz.reself.impl.service.impl;

import kz.reself.impl.model.ProdCardItem;
import kz.reself.impl.repository.ProdCardItemRepo;
import kz.reself.impl.service.IProdCardItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdCardItemServiceImpl implements IProdCardItemService {

  @Autowired
  private ProdCardItemRepo prodCardItemRepo;

  @Override
  public ProdCardItem getById(Long id) {
    return prodCardItemRepo.findById(id).get();
  }

  @Override
  public List<ProdCardItem> getAll() {
    return prodCardItemRepo.findAll();
  }

  @Override
  public void deleteById(Long id) {
    prodCardItemRepo.deleteById(id);
  }

  @Override
  public ProdCardItem create(ProdCardItem prodCardItem) {
    return prodCardItemRepo.saveAndFlush(prodCardItem);
  }

  @Override
  public List<ProdCardItem> saveAll(List<ProdCardItem> prodCardItem) {
    return prodCardItemRepo.saveAllAndFlush(prodCardItem);
  }

}
