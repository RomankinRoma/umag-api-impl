package kz.reself.impl.repository;

import kz.reself.impl.model.ProdCardItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdCardItemRepo extends JpaRepository<ProdCardItem, Long> {
}
