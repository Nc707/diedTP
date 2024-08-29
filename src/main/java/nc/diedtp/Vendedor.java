/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;

import java.util.ArrayList;

/**
 *
 * @author lucia
 */
public class Vendedor {
    private int id;
    private String nombre;
    private String direccion;
    private ArrayList<Coordenada> coordenadas;
  
    
    public Vendedor(int id, String nombre, String direccion){
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.coordenadas = new ArrayList<>();
        
    }
    public void añadirCoordenada(Coordenada c){
        this.coordenadas.add(c);
    }
    public Coordenada verCoordenada(int index){
        return coordenadas.get(index);
    }
    public int cantidadCoordenadas(){
        return coordenadas.size();
    }
    public void eliminarCoordenada(int index){
        this.coordenadas.remove(index);
    }
    public void eliminarCoordenada(Coordenada c){
        this.coordenadas.remove(c);
    }
    public String getDireccion(){
        return direccion;
    }
    public int getId()
   {
       return id;
   }
   public String getNombre()
   {
       return nombre;
   }
    
}
