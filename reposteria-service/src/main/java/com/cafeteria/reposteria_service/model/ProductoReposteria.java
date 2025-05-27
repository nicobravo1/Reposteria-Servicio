package com.cafeteria.reposteria_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductoReposteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String ingredientes;

    private double precio;
}

