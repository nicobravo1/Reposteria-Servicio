package com.cafeteria.reposteria_service.controller;

import com.cafeteria.reposteria_service.model.ProductoReposteria;
import com.cafeteria.reposteria_service.service.ProductoReposteriaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;
import static org.mockito.Mockito.any;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoReposteriaController.class)
public class ProductoReposteriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoReposteriaService productoReposteriaService;

    @Test
    public void testListarProductos() throws Exception {
        ProductoReposteria producto = new ProductoReposteria(1L, "Brownie", "Chocolate, huevo", 1500);
        when(productoReposteriaService.obtenerTodos()).thenReturn(Arrays.asList(producto));

        mockMvc.perform(get("/api/reposteria"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre", is("Brownie")));
    }

    @Test
    public void testObtenerProductoPorId() throws Exception {
        ProductoReposteria producto = new ProductoReposteria(1L, "Brownie", "Chocolate, huevo", 1500);
        when(productoReposteriaService.obtenerPorId(1L)).thenReturn(Optional.of(producto));

        mockMvc.perform(get("/api/reposteria/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ingredientes", is("Chocolate, huevo")));
    }

    @Test
    public void testGuardarProducto() throws Exception {
        ProductoReposteria producto = new ProductoReposteria(null, "Pie de limón", "Limón, leche condensada", 1800);
        when(productoReposteriaService.guardar(any(ProductoReposteria.class)))
                .thenReturn(new ProductoReposteria(2L, "Pie de limón", "Limón, leche condensada", 1800));

        mockMvc.perform(post("/api/reposteria")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "nombre": "Pie de limón",
                                    "ingredientes": "Limón, leche condensada",
                                    "precio": 1800
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Pie de limón")));
    }

    @Test
    public void testEliminarProducto() throws Exception {
        doNothing().when(productoReposteriaService).eliminar(1L);

        mockMvc.perform(delete("/api/reposteria/1"))
                .andExpect(status().isNoContent());
    }
}
