package com.techlab.perfumeria.laperfu.model;

public class Shampoo extends Producto implements Vendible, Etiquetables {
    private String tipoCabello;

    public Shampoo() {
        super();
    }

    public Shampoo (String nombre, Double precio, int stock, String tipoCabello) {
        super (nombre, precio, stock);
        this.tipoCabello = tipoCabello;
    }

    public String getTipoCabello() { return tipoCabello; }
    public void setTipoCabello(String tipoCabello) { this.tipoCabello = tipoCabello; }

    @Override 
    public String getCategoria() { return "shampoo"; }

    @Override
    public void aplicarDescuento(Double porcentaje) {
        System.out.println("Aplicando " + porcentaje + "% de descuento a " + getNombre());
    }

    @Override
    public void generarEtiqueta() {
        System.out.println("Etiqueta : " + getNombre() + " - Tipo de Cabello: " + tipoCabello);
    }
}
