package com.techlab.perfumeria.laperfu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.techlab.perfumeria.laperfu.exception.ProductoNoEncontradoException;
import com.techlab.perfumeria.laperfu.model.Crema;
import com.techlab.perfumeria.laperfu.model.Perfume;
import com.techlab.perfumeria.laperfu.model.Producto;
import com.techlab.perfumeria.laperfu.model.Shampoo;
import com.techlab.perfumeria.laperfu.util.Validador;

@Service 
public class ProductoService {
    // coleccion en memoria que guarda los productos
    private List<Producto> productos = new ArrayList<>();

    private static int contadorId = 1;

    // CREATE : agregar un nuevo producto
    public Producto guardar (Producto p) {
        Validador.validarNombre(p.getNombre());
        Validador.validarPrecio(p.getPrecio());
        Validador.validarStock(p.getStock());
        Validador.validarCategoria(p.getCategoria());

        p.setId(Long.valueOf(contadorId));
        contadorId++;

        productos.add(p);
        return p;
    }

    // Read: devuelve toda la lista de productos
    public List<Producto> listarTodos(){
        return productos;
    }

    // BUSCAR por id (Cambiado el parámetro a Long para que coincida con Producto)
    public Producto obtenerPorId(Long id) {
        for(Producto p : productos){
            if(p.getId().equals(id)){ // .equals para comparar objetos Long
                return p;
            }
        }
        throw new ProductoNoEncontradoException("No se encontro un producto con el id " + id);
    }

    // UPDATE: actualiza los datos comunes y específicos de la subclase
    public Producto actualizar(Long id, Producto datos){
        // Reutilizamos obtenerPorId. Si lanza excepcion la actualizacion se cancela
        Producto p = obtenerPorId(id);

        // Validamos los nuevos datos antes de aplicarlos
        Validador.validarNombre(datos.getNombre());
        Validador.validarPrecio(datos.getPrecio());
        Validador.validarStock(datos.getStock());
        Validador.validarCategoria(datos.getCategoria());

        // Modificamos los campos comunes de la clase padre
        p.setNombre(datos.getNombre());
        p.setPrecio(datos.getPrecio());
        p.setStock(datos.getStock());

        // Lógica polimórfica: Actualizamos los atributos específicos de cada subclase
        if (p instanceof Crema && datos instanceof Crema) {
            ((Crema) p).setTipoPiel(((Crema) datos).getTipoPiel());
        } else if (p instanceof Perfume && datos instanceof Perfume) {
            ((Perfume) p).setGenero(((Perfume) datos).getGenero());
        } else if (p instanceof Shampoo && datos instanceof Shampoo) {
            ((Shampoo) p).setTipoCabello(((Shampoo) datos).getTipoCabello());
        }

        return p;
    }

    // DELETE: eliminar un producto por ID
    public void eliminar(Long id){
        Producto p = obtenerPorId(id);
        productos.remove(p);
    }

    // Método secundario de búsqueda
    public Producto buscarPorId(Long id) {
        return obtenerPorId(id);
    }
}
