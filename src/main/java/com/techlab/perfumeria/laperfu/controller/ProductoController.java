package com.techlab.perfumeria.laperfu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.perfumeria.laperfu.model.Producto;
import com.techlab.perfumeria.laperfu.service.ProductoService;



// va a contener los endpoints
// GET-POST-PUT-DELETE (el CRUD)

@RestController 
@RequestMapping ("/producto") //prefijo comun para todas las rutas de este controlador

public class ProductoController {
    private final ProductoService productoService;

    public ProductoController (ProductoService productoService) {
        this.productoService = productoService;

    }

    @GetMapping 
    public List<Producto> listarProductos() {
    return productoService.listarTodos();

    }

    @PostMapping 
    public Producto crearPoducto(@RequestBody Producto p) {
        return productoService.guardar(p);
    }

    @PutMapping ("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto p) {
        return productoService.actualizar (id, p);
    }

     @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
        return "Producto con ID " + id + " eliminado correctamente.";
    }


}
