package com.techlab.perfumeria.laperfu.model;

public class Perfume extends Producto implements Vendible, Etiquetables {
    private String genero;

    public Perfume() {
        super();
    }

    public Perfume (String nombre, Double precio, int stock, String genero) {
        super (nombre, precio, stock);
        this.genero = genero;
    }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    @Override 
    public String getCategoria() { return "perfume"; }

    @Override
    public void aplicarDescuento(Double porcentaje) {
        System.out.println("Aplicando " + porcentaje + "% de descuento a " + getNombre());
    }

    @Override
    public void generarEtiqueta() {
        System.out.println("Etiqueta : " + getNombre() + " - Perfume " + genero);
    }
}
