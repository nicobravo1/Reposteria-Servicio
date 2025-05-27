package com.cafeteria.reposteria_service.controller;

import com.cafeteria.reposteria_service.model.ProductoReposteria;
import com.cafeteria.reposteria_service.service.ProductoReposteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reposteria")
public class ProductoReposteriaController {

    @Autowired
    private ProductoReposteriaService productoService;

    @GetMapping
    public List<ProductoReposteria> listarTodos() {
        return productoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoReposteria> obtenerPorId(@PathVariable Long id) {
        return productoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProductoReposteria crearProducto(@RequestBody ProductoReposteria producto) {
        return productoService.guardar(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoReposteria> actualizarProducto(@PathVariable Long id, @RequestBody ProductoReposteria actualizado) {
        return productoService.obtenerPorId(id).map(existente -> {
            existente.setNombre(actualizado.getNombre());
            existente.setIngredientes(actualizado.getIngredientes());
            existente.setPrecio(actualizado.getPrecio());
            return ResponseEntity.ok(productoService.guardar(existente));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
