package kz.reself.impl.repository;

import kz.reself.impl.model.impl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface implRepo extends JpaRepository<impl, Long> {
}
