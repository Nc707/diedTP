/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;


public abstract class ItemMenu {
    private int id;
    private String nombre;
    private String descripcion;
    private float precio;
    private Categoria categoria;
    
    public abstract float pesoItem(float peso);
    public abstract boolean esComida();
    public abstract boolean aptoVegano();
    public abstract boolean esBebida();
   
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public Categoria getCategoria() {
        return this.categoria;
    }
}
