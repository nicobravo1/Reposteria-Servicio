package com.cafeteria.reposteria_service.service;

import com.cafeteria.reposteria_service.model.ProductoReposteria;
import com.cafeteria.reposteria_service.repository.ProductoReposteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoReposteriaService {

    @Autowired
    private ProductoReposteriaRepository productoRepo;

    public List<ProductoReposteria> obtenerTodos() {
        return productoRepo.findAll();
    }

    public Optional<ProductoReposteria> obtenerPorId(Long id) {
        return productoRepo.findById(id);
    }

    public ProductoReposteria guardar(ProductoReposteria producto) {
        return productoRepo.save(producto);
    }

    public void eliminar(Long id) {
        productoRepo.deleteById(id);
    }
}

