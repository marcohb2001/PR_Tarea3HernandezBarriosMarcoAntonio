package com.marcoantonio.hernandezbarrios.pr_tarea3hernandezbarriosmarcoantonio;

/**
 * Clase que representa un producto dentro del supermercado.
 * Contiene toda la información necesaria para mostrar, insertar,
 * eliminar y modificar productos en la aplicación.
 *
 * Campos:
 *  - disponible: indica si el producto está disponible para la venta.
 *  - nombre: nombre del producto.
 *  - precio: precio de venta del producto.
 *  - ingredientes: lista de ingredientes del producto.
 *  - peso: peso neto del producto en gramos.
 *
 * Esta clase actúa como modelo de datos (POJO).
 */
public class Producto {

    private boolean disponible;
    private String nombre;
    private float precio;
    private String ingredientes;
    private int peso;

    /**
     * Constructor completo del producto.
     *
     * @param disponible  true si el producto está disponible
     * @param nombre      nombre del producto
     * @param precio      precio del producto
     * @param ingredientes ingredientes del producto
     * @param peso        peso neto en gramos
     */
    public Producto(boolean disponible, String nombre, float precio, String ingredientes, int peso) {
        this.disponible = disponible;
        this.nombre = nombre;
        this.precio = precio;
        this.ingredientes = ingredientes;
        this.peso = peso;
    }

    /**
     * @return true si el producto está disponible
     */
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    /**
     * @return nombre del producto
     */
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return precio del producto
     */
    public float getPrecio() { return precio; }
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * @return ingredientes del producto
     */
    public String getIngredientes() {
        return ingredientes;
    }
    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    /**
     * @return peso neto del producto en gramos
     */
    public int getPeso() {
        return peso;
    }
    public void setPeso(int peso) {
        this.peso = peso;
    }
}

