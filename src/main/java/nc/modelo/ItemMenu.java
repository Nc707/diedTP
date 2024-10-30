/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.modelo;

import java.util.HashSet;
import java.util.List;
import nc.modelo.excepciones.CategoriaIncompatibleException;

public abstract class ItemMenu {

    private int id;
    private static int next_id=0;
    private String nombre;
    private String descripcion;
    private float precio;
    private Vendedor vendedor;
    protected HashSet<Categoria> categorias;
    
    public ItemMenu(String nombre, float precio, Vendedor vendedor){
        this.setNombre(nombre);
        this.setPrecio(precio);
        this.setId();
        this.descripcion = new String();
        this.categorias = new HashSet();
        this.vendedor = vendedor;
    }
    public String getNombre(){
        return nombre;
    }
    private void setNombre(String nombre) {
        this.nombre = nombre;
    }
    private void setPrecio(float precio) {
        this.precio = precio;
    }
    private void setId(){
        this.id = next_id;
        next_id++;
    }
    
    public Vendedor getVendedor(){
        return vendedor;
    }
    @Override
    public String toString(){
        return nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public boolean tieneCategoria(Categoria cat){
        return this.categorias.contains(cat);
    }
    public boolean tieneCategorias(List<Categoria> categorias){
        boolean cumple = true;
        for(Categoria cat: categorias){
            if(!this.tieneCategoria(cat)) cumple = false;
        }
        return cumple;
    }
    
    public boolean tieneCategoria(String cat){
        Categoria aux = Categoria.getCategoria(cat);
        if(aux!=null) return this.categorias.contains(aux);
        return false;
    }
    public HashSet<Categoria> getCategorias(){
        return (HashSet<Categoria>) this.categorias.clone();
    }
    public int getId(){
        return this.id;
    }
    public float getPrecio(){
        return this.precio;
    }
    public String getDescripcion(){
        return this.descripcion;
    }
    public abstract void addCategoria(String cat) throws CategoriaIncompatibleException;
    public abstract boolean removeCategoria(String cat);
    public int cantidadCategorias(){
        return this.categorias.size();
    }
    //public abstract float pesoItem(float peso);
    //quito metodo abstracto y solo lo implemento en bebida porq en comida se guarda directo como atributo
    public abstract boolean esComida();

    public abstract boolean aptoVegano();

    public abstract boolean esBebida();
}
