/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;

import java.util.HashSet;

public abstract class ItemMenu {

    private int id;
    private static int next_id=0;
    private String nombre;
    private String descripcion;
    private float precio;
    protected HashSet<Tag> tags;
    
    public ItemMenu(String nombre, float precio){
        this.setNombre(nombre);
        this.setPrecio(precio);
        this.setId();
        this.descripcion = new String();
        this.tags = new HashSet();
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
    @Override
    public String toString(){
        return nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public boolean hasTag(Tag tag){
        return this.tags.contains(tag);
    }
    public boolean hasTag(String tag){
        Tag aux = Tag.getTag(tag);
        if(aux!=null) return this.tags.contains(aux);
        return false;
    }
    public HashSet<Tag> getTags(){
        return (HashSet<Tag>) this.tags.clone();
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
    public abstract boolean addTag(String tag);
    public abstract boolean removeTag(String tag);
    //public abstract float pesoItem(float peso);
    //quito metodo abstracto y solo lo implemento en bebida porq en comida se guarda directo como atributo
    public abstract boolean esComida();

    public abstract boolean aptoVegano();

    public abstract boolean esBebida();
}
