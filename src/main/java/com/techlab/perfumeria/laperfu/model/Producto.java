package com.techlab.perfumeria.laperfu.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.techlab.perfumeria.laperfu.exception.PrecioInvalidoException;
import com.techlab.perfumeria.laperfu.exception.StockInvalidoException;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, 
    include = JsonTypeInfo.As.PROPERTY, 
    property = "categoria"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Crema.class, name = "crema"),
    @JsonSubTypes.Type(value = Perfume.class, name = "perfume"),
    @JsonSubTypes.Type(value = Shampoo.class, name = "shampoo")
})
public abstract class Producto {

    public static Long contadorId = 0L;
    private static int totalProductos = 0;

    private Long id;
    private String nombre;
    private Double precio;
    private int stock;

    // Constructor vacío exigido por Jackson
    protected Producto() {
    }

    public Producto (String nombre, Double precio, int stock){
        if (precio == null || precio <= 0) {
            throw new PrecioInvalidoException("El precio debe ser mayor a cero.");
        }
        if (stock < 0) {
            throw new StockInvalidoException("El stock inicial no puede ser negativo.");
        }
        this.id = ++contadorId;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        totalProductos++;
    }     

    public abstract String getCategoria();
    public abstract void aplicarDescuento(Double porcentaje);

    // Getters y Setters Explícitos y Seguros
    public Long getId() { return id; }
    
    public void setId(Long id) { this.id = id; } // Cambiado a Long para resolver tu error de Linea 51 en ProductoService

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) {
        if (precio == null || precio <= 0) {
            throw new PrecioInvalidoException("El nuevo precio debe ser mayor a cero.");
        }
        this.precio = precio;
    }

    public int getStock() { return stock; }
    public void setStock(int stock) {
        if (stock < 0) {
            throw new StockInvalidoException("El stock modificado no puede ser negativo.");
        }
        this.stock = stock;
    }

    public static int getTotalProductos(){ return totalProductos; }

    public void mostrarDatos() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: " + precio);
        System.out.println("Stock: " + stock);
    }
}
