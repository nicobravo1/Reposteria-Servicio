package com.cafeteria.reposteria_service.repository;

import com.cafeteria.reposteria_service.model.ProductoReposteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoReposteriaRepository extends JpaRepository<ProductoReposteria, Long> {
}
