package com.techlab.perfumeria.laperfu.model;

public class Crema extends Producto implements Vendible, Etiquetables {

    private String tipoPiel;

    public Crema() {
        super();
    }

    public Crema(String nombre, Double precio, int stock, String tipoPiel) {
        super(nombre, precio, stock);
        this.tipoPiel = tipoPiel;
    }

    public String getTipoPiel() { return tipoPiel; }
    public void setTipoPiel(String tipoPiel) { this.tipoPiel = tipoPiel; }

    @Override
    public String getCategoria() { return "crema"; }

    @Override
    public void aplicarDescuento(Double porcentaje) {
        System.out.println("Aplicando " + porcentaje + "% de descuento a " + getNombre());
    }

    @Override
    public void generarEtiqueta() {
        System.out.println("Etiqueta : " + getNombre() + " - Tipo de Piel " + tipoPiel);
    }
}
