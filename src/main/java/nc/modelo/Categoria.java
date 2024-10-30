/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.modelo;

import java.util.HashSet;

public class Categoria {
    private static final HashSet<Categoria> allCategoria = new HashSet();
    public static final Categoria categoriaPlatos = new Categoria("plato");
    public static final Categoria categoriaBebidas = new Categoria("bebida");
    static{
        allCategoria.add(categoriaPlatos);
        allCategoria.add(categoriaBebidas);
    }
    private final String categoria;
    
    public static Categoria getCategoria(String nombreCategoria){
        String cat = nombreCategoria.toLowerCase();
        for(Categoria tagBusqueda: allCategoria){
            if(tagBusqueda.toString().equals(cat)){
                return tagBusqueda;
            }
        }
        return createCategoria(cat);
    }
    private static Categoria createCategoria(String cat){
        Categoria aux =new Categoria(cat);
        allCategoria.add(aux);
        return aux;
        
    }
    public static boolean deleteCategoria(String cat){
        if(cat.equals(categoriaPlatos.toString()) || cat.equals(categoriaBebidas.toString())) return false;
        Categoria removeCategoria = getCategoria(cat);
        if(removeCategoria == null) return false;
        allCategoria.remove(removeCategoria);
        return true;
    }
    
    private Categoria(String cat){
        this.categoria = cat;
    }
    @Override
    public String toString(){
        return categoria;
    }
    
}
